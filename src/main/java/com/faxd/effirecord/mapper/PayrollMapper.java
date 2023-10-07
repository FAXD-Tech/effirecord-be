package com.faxd.effirecord.mapper;

import com.faxd.effirecord.dto.payroll.PayrollInfoDto;
import com.faxd.effirecord.dto.payroll.PayrollPostDto;
import com.faxd.effirecord.model.Payroll;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayrollMapper {
    Payroll payrollPostDtoToPayroll(PayrollPostDto payrollPostDto);

    PayrollInfoDto payrollToPayrollInfoDto(Payroll payroll);

}
