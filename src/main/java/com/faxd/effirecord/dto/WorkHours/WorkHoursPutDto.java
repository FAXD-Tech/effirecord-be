package com.faxd.effirecord.dto.WorkHours;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHoursPutDto {

    @NotNull
    @Positive
    private Double hoursWorked;

    @PastOrPresent
    @NotNull
    private LocalDate attendanceDate;

    @NotBlank
    private String comments;
}
