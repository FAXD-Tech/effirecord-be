package com.faxd.effirecord.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class EmployeeInfoDto {
    private long id;
    private String name;
    private String phone;
}