package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.employee.EmployeeSlimInfoDto;
import com.faxd.effirecord.dto.project.ProjectAddEmployeeDto;
import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.exception.CompanyNotFoundException;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.exception.ProjectNotFoundException;
import com.faxd.effirecord.mapper.EmployeeMapper;
import com.faxd.effirecord.mapper.ProjectMapper;
import com.faxd.effirecord.model.Project;
import com.faxd.effirecord.repository.CompanyRepository;
import com.faxd.effirecord.repository.EmployeeRepository;
import com.faxd.effirecord.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public void createProject(final ProjectPostDto projectPostDto){
        companyRepository.findById(projectPostDto.getCompanyId())
                .map(company -> {
                    if(company.getIsDeleted() || !company.isVerified()){
                        throw new CompanyNotFoundException(projectPostDto.getCompanyId());
                    }
                    Project project = projectMapper.projectPostDtoToProject(projectPostDto);
                    project.setCompany(company);
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new CompanyNotFoundException(projectPostDto.getCompanyId()));

    }

    public void addEmployees(ProjectAddEmployeeDto projectAddEmployeeDto) {
        projectRepository.findById(projectAddEmployeeDto.getProjectId())
                .ifPresentOrElse(project ->
                                projectAddEmployeeDto.getEmployeeIds()
                                        .forEach(id ->
                                                employeeRepository.findById(id)
                                                        .ifPresentOrElse(employee -> employee.setCurrentProject(project),
                                                                () -> {
                                                                    throw new EmployeeNotFoundException(id);
                                                                })),
                        () -> {
                            throw new ProjectNotFoundException(projectAddEmployeeDto.getProjectId());
                        });
    }

    public ProjectInfoDto getAllEmployees(Long projectId) {
        return projectRepository.findByIdAndIsDeletedFalseAndIsVerifiedTrue(projectId)
                .map(project -> {
                    ProjectInfoDto projectInfoDto = projectMapper.projectToProjectInfoDto(project);
                    List<EmployeeSlimInfoDto> list = project.getEmployees().stream()
                            .map(employeeMapper::employeeToEmploySlimInfoDto)
                            .toList();
                    projectInfoDto.setEmployees(list);
                    return projectInfoDto;
                })
                .orElseThrow(() -> {
                    logger.warn("Task: get all employee in the project;" +
                            " message: the project with id: {} does not found!",projectId);
                    return new ProjectNotFoundException(projectId);
                });
    }

    public void removeEmployees(Long projectId, List<Long> employeeRemoveList) {
        employeeRepository.findAllById(employeeRemoveList)
                .forEach(employee -> {
                    Long id = employee.getCurrentProject().getId();
                    if (projectId.equals(id)){
                        employee.setCurrentProject(null);
                    }
                } );
    }
}
