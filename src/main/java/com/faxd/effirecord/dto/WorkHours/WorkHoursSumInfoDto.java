package com.faxd.effirecord.dto.WorkHours;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkHoursSumInfoDto {

    private List<WorkHoursMonthlyInfoDto> monthlyInfoDtos;
    private Double total;

    public void getSumFromMonthly(WorkHoursMonthlyInfoDto monthlyInfoDtos){
        this.total += monthlyInfoDtos.getHoursWorkedTotal();
    }
}
