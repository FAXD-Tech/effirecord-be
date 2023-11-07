package com.faxd.effirecord.mapper;

import com.faxd.effirecord.dto.WorkHours.WorkHoursInfoDto;
import com.faxd.effirecord.dto.WorkHours.WorkHoursNoDatePostDto;
import com.faxd.effirecord.model.WorkHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkHoursMapper {
    WorkHours workHoursNoDatePostDtoToWorkHours(WorkHoursNoDatePostDto workHoursPostDto);

    @Mapping(target = "projectInfo", source = "project")
    WorkHoursInfoDto workHoursToWorkHoursInfoDto(WorkHours workHours);
}
