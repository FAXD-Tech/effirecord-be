package com.faxd.effirecord.controller;


import com.faxd.effirecord.dto.project.ProjectAddEmployeeDto;
import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
@RequiredArgsConstructor
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("")
    public ProjectInfoDto createProject(@RequestBody ProjectPostDto projectPostDto){
        return projectService.createProject(projectPostDto);
    }

    @PostMapping("/employee/add")
    public void addEmployees(@RequestBody ProjectAddEmployeeDto projectAddEmployeeDto){
        projectService.addEmployees(projectAddEmployeeDto);
    }
}
