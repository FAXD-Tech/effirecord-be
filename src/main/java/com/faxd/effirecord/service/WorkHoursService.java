package com.faxd.effirecord.service;

import com.faxd.effirecord.database.DatabaseManagementService;
import com.faxd.effirecord.dto.WorkHours.WorkHoursInfoDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursMonthlyInfoDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursNoDatePostDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursPostDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursPutDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursSumInfoDto;
import com.faxd.effirecord.dto.project.ProjectSlimInfoDto;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.exception.ProjectNotFoundException;
import com.faxd.effirecord.exception.WorkHoursNotFoundException;
import com.faxd.effirecord.mapper.ProjectMapper;
import com.faxd.effirecord.mapper.WorkHoursMapper;
import com.faxd.effirecord.model.Employee;
import com.faxd.effirecord.model.Project;
import com.faxd.effirecord.model.WorkHours;
import com.faxd.effirecord.repository.EmployeeRepository;
import com.faxd.effirecord.repository.ProjectRepository;
import com.faxd.effirecord.repository.WorkHoursRepository;
import com.faxd.effirecord.utils.BeanHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WorkHoursService {

    private String lastPartitionWorkHoursTable;
    private final Logger logger = LoggerFactory.getLogger(WorkHoursService.class);
    private final WorkHoursMapper workHoursMapper;
    private final WorkHoursRepository workHoursRepository;
    private final DatabaseManagementService databaseManagementService;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public void createWorkHours(final Long projectId, final WorkHoursPostDto workHoursPostDto) {
        LocalDate attendanceDate = workHoursPostDto.getAttendanceDate();
        List<WorkHoursNoDatePostDto> workHoursNoDatePostDtoList = workHoursPostDto.getWorkHoursPostDtoList();

        // create partition table for work_hours if this table not exist
        String attendanceMonth = attendanceDate.format(DateTimeFormatter.ofPattern("yyyy_MM"));
        if (!attendanceMonth.equalsIgnoreCase(lastPartitionWorkHoursTable)){
            databaseManagementService.createNewWorkHoursPartition(attendanceDate);
            lastPartitionWorkHoursTable = attendanceMonth;
        }

        // get all employee ids prepared for batch fetch from database
        Set<Long> employeeIdSet = workHoursNoDatePostDtoList.stream()
                .map(WorkHoursNoDatePostDto::getEmployeeId)
                .collect(Collectors.toSet());

        //create map for id -> employee
        Map<Long, Employee> employeeMap = employeeRepository
                .findAllByIdInAndCurrentProjectIdAndIsDeletedFalse(employeeIdSet, projectId)
                .map(this::getIdMappingEmployee)
                .orElse(Collections.emptyMap());

        Optional<Project> validProject = projectRepository.findByIdAndIsDeletedFalseAndIsVerifiedTrue(projectId);
        Project project = validProject.orElseThrow(() -> new ProjectNotFoundException(projectId));
        List<WorkHours> workHours = mappingworkHoursPostDtoListToWorkHoursList(project, workHoursNoDatePostDtoList, employeeMap, attendanceDate);
        workHoursRepository.saveAll(workHours);
    }

    private Map<Long, Employee> getIdMappingEmployee(final List<Employee> employees){
        return employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    private Employee getEmployeeByKeyFromMap(final Map<Long, Employee> employeeMap, final Long employeeId){
        return Optional.ofNullable(employeeMap)
                .map(map -> map.get(employeeId))
                .orElseThrow(() ->{
                    logger.warn("Task: get employee from map by key; " +
                            "error: the employee with id: {} does not exist!", employeeId);
                    return new EmployeeNotFoundException(employeeId);
                });
    }

    private List<WorkHours> mappingworkHoursPostDtoListToWorkHoursList(final Project project,
                                                                       final List<WorkHoursNoDatePostDto> workHoursPostDtoList,
                                                                       final Map<Long, Employee> employeeMap,
                                                                       final LocalDate attendanceDate) {
        String attendanceMonth = attendanceDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        return workHoursPostDtoList.stream()
                .map(workHoursPostDto -> {
                    WorkHours workHours = workHoursMapper.workHoursNoDatePostDtoToWorkHours(workHoursPostDto);
                    Long employeeId = workHoursPostDto.getEmployeeId();
                    workHours.setEmployee(getEmployeeByKeyFromMap(employeeMap, employeeId));
                    workHours.setAttendanceDate(attendanceDate);
                    workHours.setProject(project);
                    workHours.setPartitionKey(attendanceMonth);
                    //TODO fix the createby and updateBy
                    workHours.setCreatedBy(getEmployeeByKeyFromMap(employeeMap, employeeId));
                    workHours.setUpdatedBy(getEmployeeByKeyFromMap(employeeMap, employeeId));
                    return workHours;
                })
                .toList();
    }

    public List<WorkHoursInfoDto> getWorkHoursForEmployeeInPeriod(final Long employeeId,
                                                                  final LocalDate start,
                                                                  final LocalDate end) {

        return workHoursRepository.findAllByEmployeeIdInAndIsDeletedFalseAndAttendanceDateBetween(Set.of(employeeId), start, end)
                .stream()
                .map(workHoursMapper::workHoursToWorkHoursInfoDto)
                .toList();
    }

    public WorkHoursSumInfoDto getMonthlyWorkHoursForEmployeeInPeriod(final Long employeeId,
                                                                      final LocalDate start,
                                                                      final LocalDate end) {
        List<Object[]> objects = workHoursRepository.sumWorkedHoursByDateRangeAndPartition(employeeId, start, end);
        List<Long> projectIdList = objects.stream().map(objects1 -> (Long) objects1[0]).toList();

        Map<Long, ProjectSlimInfoDto> projectSlimInfoDtoMap = projectRepository.findAllById(projectIdList)
                .stream()
                .map(projectMapper::projectToProjectSlimInfoDto)
                .collect(Collectors.toMap(ProjectSlimInfoDto::getId,Function.identity()));

        objects.forEach(object -> {
            Long projectId = (Long) object[0];
            ProjectSlimInfoDto project = projectSlimInfoDtoMap.get(projectId);
            object[0] = project;
        });

        List<WorkHoursMonthlyInfoDto> workHoursMonthlyInfoDtoList = objects.stream()
                .map(WorkHoursMonthlyInfoDto::from)
                .toList();

        Double totalHours = workHoursMonthlyInfoDtoList.stream()
                .mapToDouble(WorkHoursMonthlyInfoDto::getHoursWorkedTotal)
                .sum();

        return WorkHoursSumInfoDto.builder()
                .monthlyInfoDtos(workHoursMonthlyInfoDtoList)
                .total(totalHours)
                .build();
    }

    public WorkHoursInfoDto updateWorkHours(Long workHoursId, WorkHoursPutDto workHoursPutDto) {
        return workHoursRepository.findByIdAndIsDeletedFalse(workHoursId)
                .map(workHours -> {
                    BeanHelper.CopyDtoIntoEntity(workHours, workHoursPutDto);
                    String partitionKey = workHoursPutDto.getAttendanceDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
                    workHours.setPartitionKey(partitionKey);

                    // TODO  updateBy
                    workHoursRepository.save(workHours);
                    return workHoursMapper.workHoursToWorkHoursInfoDto(workHours);
                })
                .orElseThrow(() -> {
                    logger.info("Task: update work hours Record; error: Can not found any work hours record with id: {}", workHoursId);
                    return new WorkHoursNotFoundException(workHoursId);
                });
    }

    public void deleteWorkHours(List<Long> workHoursIds) {
        List<WorkHours> list = workHoursRepository.findAllById(workHoursIds).stream().map(workHours -> {
            workHours.setIsDeleted(true);
            // TODO  updateBy
            return workHours;
        }).toList();
        workHoursRepository.saveAll(list);
    }
}
