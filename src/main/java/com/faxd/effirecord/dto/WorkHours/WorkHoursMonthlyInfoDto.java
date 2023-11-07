package com.faxd.effirecord.dto.WorkHours;

import com.faxd.effirecord.dto.project.ProjectSlimInfoDto;
import com.faxd.effirecord.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHoursMonthlyInfoDto {

    private ProjectSlimInfoDto projectInfo;
    private String month;
    private Double hoursWorkedTotal;

    public static WorkHoursMonthlyInfoDto from(Object[] data) {
        if (data == null || data.length < 3) {
            return null;
        }
        return new WorkHoursMonthlyInfoDto((ProjectSlimInfoDto) data[0], (String) data[1], (Double) data[2]);
    }

}
