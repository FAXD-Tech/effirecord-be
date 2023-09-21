package com.faxd.effirecord.dto.company;

import com.faxd.effirecord.dto.project.ProjectInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyInfoDto {
    private Long id;
    private String name;
    private List<ProjectInfoDto> projects;
}
