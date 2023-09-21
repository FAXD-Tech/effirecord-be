package com.faxd.effirecord.service;

import com.faxd.effirecord.dto.project.ProjectAddEmployeeDto;
import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.exception.CompanyNotFoundException;
import com.faxd.effirecord.exception.EmployeeNotFoundException;
import com.faxd.effirecord.exception.ProjectNotFoundException;
import com.faxd.effirecord.mapper.ProjectMapper;
import com.faxd.effirecord.model.Project;
import com.faxd.effirecord.repository.CompanyRepository;
import com.faxd.effirecord.repository.EmployeeRepository;
import com.faxd.effirecord.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectInfoDto createProject(final ProjectPostDto projectPostDto){
        Project savedProject = companyRepository.findById(projectPostDto.getCompanyId())
                .map(company -> {
                    if(company.getIsDeleted() || !company.isVerified()){
                        throw new CompanyNotFoundException(projectPostDto.getCompanyId());
                    }
                    Project project = projectMapper.projectPostDtoToProject(projectPostDto);
                    project.setCompany(company);
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new CompanyNotFoundException(projectPostDto.getCompanyId()));
        return projectMapper.projectToProjectInfoDto(savedProject);
    }

    public void addEmployees(ProjectAddEmployeeDto projectAddEmployeeDto) {
        projectRepository.findById(projectAddEmployeeDto.getProjectId())
                .ifPresentOrElse(project ->
                                projectAddEmployeeDto.getEmployeeIds()
                                        .forEach(id ->
                                                employeeRepository.findById(id)
                                                        .ifPresentOrElse(employee -> employee.setProjects(project),
                                                                () -> {
                                                                    throw new EmployeeNotFoundException(id);
                                                                })),
                        () -> {
                            throw new ProjectNotFoundException(projectAddEmployeeDto.getProjectId());
                        });
    }
}
