package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.employee.EmployeeInfoDto;
import com.faxd.effirecord.dto.employee.EmployeePostDto;
import com.faxd.effirecord.dto.employee.EmployeePutDto;
import com.faxd.effirecord.service.EmployeeService;
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
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    @PostMapping("")
    public EmployeeInfoDto signIn(@RequestBody EmployeePostDto employeePostDto){
        return employeeService.signIn(employeePostDto);
    }

    @GetMapping("/{id}")
    public EmployeeInfoDto getEmployeeInfo(@PathVariable Long id){
        return employeeService.getEmployeeInfo(id);
    }

    @PutMapping("/{id}")
    public void updateEmployeeInfo( @PathVariable Long id, @RequestBody EmployeePutDto employeePutDto){
        employeeService.upsertEmployeeInfo(id, employeePutDto);
    }

    @PutMapping("/{id}/verify")
    public void verifyEmployee(@PathVariable Long id){
        employeeService.verifyEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployedById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
}
