package com.faxd.effirecord.mapper;

import com.faxd.effirecord.dto.project.ProjectInfoDto;
import com.faxd.effirecord.dto.project.ProjectPostDto;
import com.faxd.effirecord.dto.project.ProjectSlimInfoDto;
import com.faxd.effirecord.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    Project projectPostDtoToProject(ProjectPostDto projectPostDto);

    ProjectInfoDto projectToProjectInfoDto(Project project);
    ProjectSlimInfoDto projectToProjectSlimInfoDto(Project project);

}
