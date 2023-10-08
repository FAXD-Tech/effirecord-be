package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.project.ProjectAddEmployeeDto;
import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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
    public void createProject(@Valid @RequestBody ProjectPostDto projectPostDto){
        projectService.createProject(projectPostDto);
    }

    @Operation(
            summary = "Add some employees for a project",
            description = "Add some employees into project by specifying their id. Response nothing when succeed")
    @PostMapping("/employee/add")
    public void addEmployees(@Valid @RequestBody ProjectAddEmployeeDto projectAddEmployeeDto){
        projectService.addEmployees(projectAddEmployeeDto);
    }

    @Operation(
            summary = "Get current employees for a project",
            description = "Get All employees by specifying project id. Response a list of employees")
    @GetMapping("/{projectId}/employee")
    public ProjectInfoDto getAllEmployees(@PathVariable Long projectId){
       return projectService.getAllEmployees(projectId);
    }

    @Operation(
            summary = "Remove some employees from a project",
            description = "Remove some employees by list of employee id.")
    @PutMapping("/{projectId}/employee")
    public void removeEmployees(@PathVariable Long projectId, @RequestParam List<Long> employeeRemoveList){
        projectService.removeEmployees(projectId, employeeRemoveList);
    }
}
