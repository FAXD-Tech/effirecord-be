package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.project.ProjectAddEmployeeDto;
import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("project")
@RequiredArgsConstructor
@Validated
@Tag(name = "project", description = "Project management APIs")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(
            summary = "Create project",
            description = "post project information to create a project. The response is project information when is successful")
    @PostMapping("")
    public ProjectInfoDto createProject(@RequestBody ProjectPostDto projectPostDto){
        return projectService.createProject(projectPostDto);
    }

    @Operation(
            summary = "Add some employees for a project",
            description = "Add some employees into project by specifying their id. Response nothing when succeed")
    @PostMapping("/employee/add")
    public void addEmployees(@RequestBody ProjectAddEmployeeDto projectAddEmployeeDto){
        projectService.addEmployees(projectAddEmployeeDto);
    }
}
