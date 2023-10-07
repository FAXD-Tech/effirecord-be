package com.faxd.effirecord.controller;

import com.faxd.effirecord.dto.payroll.PayrollInfoDto;
import com.faxd.effirecord.dto.payroll.PayrollPostDto;
import com.faxd.effirecord.model.Payroll;
import com.faxd.effirecord.service.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("payroll")
@RequiredArgsConstructor
@Validated
@Tag(name = "payroll", description = "Payroll management APIs")
public class PayrollController {

    private final PayrollService payrollService;

    @Operation(
            summary = "Create payroll record",
            description = "Create payroll record.")
    @PostMapping("")
    public void createPayroll(@RequestBody ArrayList<PayrollPostDto> payrollPostDtoListList){
        payrollService.createPayroll(payrollPostDtoListList);
    }

    @Operation(
            summary = "Retrieve payroll record",
            description = "Retrieve payroll record by payee id in a period. The response is a list of payroll record")
    @GetMapping("/{payeeId}")
    public List<PayrollInfoDto> getPayrollRecordByPayeeId(@PathVariable Long payeeId,
                                                          @RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                                          @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return payrollService.getPayrollRecordByPayeeId(payeeId, start, end);
    }

    @Operation(
            summary = "Update payroll record",
            description = "Update payroll record by payroll id. " +
                    "The original one will be marked deleted and the updated record will be added")
    @PostMapping("/{payrollId}")
    public void updatePayrollById(@PathVariable Long payrollId, @RequestBody PayrollPostDto payrollPostDto){
        payrollService.updatePayroll(payrollId, payrollPostDto);
    }

    @Operation(
            summary = "Delete payroll record",
            description = "Delete payroll record by payroll id. The payroll will be marked deleted")
    @PutMapping("/{payrollId}")
    public void deletePayrollById(@PathVariable Long payrollId){
        payrollService.deletePayroll(payrollId);
    }



}
