package com.faxd.effirecord.mapper;


import com.faxd.effirecord.dto.employee.EmployeeInfoDto;
import com.faxd.effirecord.dto.employee.EmployeePostDto;
import com.faxd.effirecord.dto.employee.EmployeePutDto;
import com.faxd.effirecord.model.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    Employees employeePostDtoToEmployees(EmployeePostDto employeePostDto);

    Employees employeePutDtoToEmployees(EmployeePutDto employeePutDto);

    EmployeeInfoDto employeesToEmployInfoDto(Employees employees);


}
