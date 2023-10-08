package com.faxd.effirecord.dto.project;

import com.faxd.effirecord.dto.employee.EmployeeSlimInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfoDto {
    private Long id;
    private String name;
    private List<EmployeeSlimInfoDto> employees;
}
