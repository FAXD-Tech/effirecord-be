package com.faxd.effirecord.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmployeePutDto {

    @NotBlank
    @Size(max = 255, message = "Username can not be more than 255 characters.")
    private String name;

    @NotBlank(message = "Phone must not be empty.")
    @Pattern(regexp ="^((13)|(14)|(15)|(16)|(17)|(18))\\d{9}$",
                message = "phone number must start with 13, 14, 15, 16, 17 or 18 with another 9 digits ")
    @Size(max = 15, message = "phone can not be more than 15 characters.")
    private String phone;

    @NotEmpty(message = "Password must  not be empty")
    @Pattern(regexp = "^(?=\\S*[a-zA-Z])(?=\\S*[0-9#!\"$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]).{8,}$",
            message = "Your password must be at least 8 character long and contains at least one non-letter character.")
    private String password;
}