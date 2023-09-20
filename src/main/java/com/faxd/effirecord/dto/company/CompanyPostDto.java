package com.faxd.effirecord.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyPostDto {
    @NotBlank
    @Size(max = 255, message = "Username can not be more than 255 characters.")
    private String name;
}
