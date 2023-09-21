package com.faxd.effirecord.dto.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAddEmployeeDto {
    @NotEmpty
    private Long projectId;

    @NotEmpty
    private List<Long> employeeIds;
}
