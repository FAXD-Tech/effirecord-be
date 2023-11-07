package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.WorkHours.WorkHoursInfoDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursPostDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursPutDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursSumInfoDto;
import com.faxd.effirecord.service.WorkHoursService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("work_hours")
@RequiredArgsConstructor
@Validated
@Tag(name = "work hours", description = "Work hours management APIs")
public class WorkHoursController {

    private final WorkHoursService workHoursService;

    @Operation(
            summary = "Create a list of work hours record",
            description = "post a list of work hours to create a work hours for employee.")
    @PostMapping("/{projectId}")
    public void createWorkHours(@PathVariable Long projectId,
                                @Valid @RequestBody WorkHoursPostDto workHoursPostDto) {

        workHoursService.createWorkHours(projectId, workHoursPostDto);
    }

    @Operation(
            summary = "Get a list of work hours record for an employee",
            description = "Get a list of work hours for an employee by Id. Returned a list a record")
    @GetMapping("/{employeeId}")
    public List<WorkHoursInfoDto> getWorkHoursForEmployeeInPeriod(@PathVariable Long employeeId,
                                                                  @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                  @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        return workHoursService.getWorkHoursForEmployeeInPeriod(employeeId, start, end);
    }

    @Operation(
            summary = "Get a list of monthly and total work hours record for an employee",
            description = "Get a list of monthly work hours for an employee by Id. Returned a list of record")
    @GetMapping("/sum/{employeeId}")
    public WorkHoursSumInfoDto getMonthlyWorkHoursForEmployeeInPeriod(@PathVariable Long employeeId,
                                                                      @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                                      @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        return workHoursService.getMonthlyWorkHoursForEmployeeInPeriod(employeeId, start, end);
    }

    @Operation(
            summary = "Update work hours record for an employee",
            description = "Update work hours record for an employee. Returned the new record")
    @PutMapping("/{workHoursId}")
    public WorkHoursInfoDto updateWorkHours(@PathVariable Long workHoursId,
                                            @Valid @RequestBody WorkHoursPutDto workHoursPutDto) {

        return workHoursService.updateWorkHours(workHoursId, workHoursPutDto);
    }

    @Operation(
            summary = "delete work hours record",
            description = "delete work hours record.")
    @PutMapping("/")
    public void deleteWorkHours(@RequestBody List<Long> workHoursIds) {

        workHoursService.deleteWorkHours(workHoursIds);
    }
}
