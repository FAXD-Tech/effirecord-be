package com.faxd.effirecord.dto.WorkHours;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHoursNoDatePostDto {

    @NotNull
    private Long employeeId;

    @NotNull
    @Positive
    private Double hoursWorked;

    private String comments;
}
