package com.faxd.effirecord.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProjectPostDto {

    @NotBlank
    @Size(max = 255, message = "project name can not be more than 255 characters.")
    private String name;

    @NotEmpty
    private Long companyId;
}
