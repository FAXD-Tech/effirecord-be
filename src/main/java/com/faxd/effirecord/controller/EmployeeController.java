package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.employee.EmployeeInfoDto;
import com.faxd.effirecord.dto.employee.EmployeePostDto;
import com.faxd.effirecord.dto.employee.EmployeePutDto;
import com.faxd.effirecord.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
@Validated
@Tag(name = "employee", description = "Employee management APIs")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @Operation(
            summary = "Employee sign in",
            description = "Post some employee information to create an employee. The response is employee information when is successful")
    @PostMapping("")
    public EmployeeInfoDto signIn(@RequestBody EmployeePostDto employeePostDto){
        return employeeService.signIn(employeePostDto);
    }

    @Operation(
            summary = "Retrieve an employee",
            description = "Retrieve employee information by specifying its id. The response is employee information when is successful")
    @GetMapping("/{id}")
    public EmployeeInfoDto getEmployeeInfo(@PathVariable Long id){
        return employeeService.getEmployeeInfo(id);
    }

    @Operation(
            summary = "Update an employee",
            description = "Update employee information by putting some updated information. The response is employee information when is successful")
    @PutMapping("/{id}")
    public void updateEmployeeInfo( @PathVariable Long id, @RequestBody EmployeePutDto employeePutDto){
        employeeService.upsertEmployeeInfo(id, employeePutDto);
    }

    @Operation(
            summary = "Verify an employee",
            description = "Verify employee by specifying its id. Response nothing when succeed")
    @PutMapping("/{id}/verify")
    public void verifyEmployee(@PathVariable Long id){
        employeeService.verifyEmployee(id);
    }

    @Operation(
            summary = "Delete an employee",
            description = "Delete employee by specifying its id. Response nothing when succeed")
    @DeleteMapping("/{id}")
    public void deleteEmployedById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
}
