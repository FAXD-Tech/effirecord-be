package com.faxd.effirecord.dto.WorkHours;

import com.faxd.effirecord.dto.project.ProjectSlimInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHoursInfoDto {

    private Long id;

    private ProjectSlimInfoDto projectInfo;

    private Double hoursWorked;

    private LocalDate attendanceDate;

    private String comments;
}
