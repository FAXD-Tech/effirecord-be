package com.faxd.effirecord.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyInfoDto {
    private Long id;
    private String name;
}
