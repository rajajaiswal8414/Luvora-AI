package com.luvora.ai.mapper;

import com.luvora.ai.dto.project.ProjectResponse;
import com.luvora.ai.dto.project.ProjectSummaryResponse;
import com.luvora.ai.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    List<ProjectSummaryResponse>  toProjectSummaryResponse(List<Project> projectList);
}
