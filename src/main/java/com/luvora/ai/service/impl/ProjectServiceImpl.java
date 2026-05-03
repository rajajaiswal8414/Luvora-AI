package com.luvora.ai.service.impl;

import com.luvora.ai.dto.project.ProjectRequest;
import com.luvora.ai.dto.project.ProjectResponse;
import com.luvora.ai.dto.project.ProjectSummaryResponse;
import com.luvora.ai.entity.Project;
import com.luvora.ai.entity.User;
import com.luvora.ai.mapper.ProjectMapper;
import com.luvora.ai.repository.ProjectRepository;
import com.luvora.ai.repository.UserRepository;
import com.luvora.ai.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        List<Project> projects = projectRepository.findActiveProjectsByUserId(userId);
        return projectMapper.toProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getUserProjectOrThrow(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest, Long userId) {

        User owner = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = Project.builder()
                .name(projectRequest.name())
                .isPublic(false)
                .owner(owner)
                .build();

        projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest, Long userId) {

        Project project = getUserProjectOrThrow(id, userId);

        project.setName(projectRequest.name());

        project =  projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {

        Project project = getUserProjectOrThrow(id, userId);
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // 🔥 Centralized method
    private Project getUserProjectOrThrow(Long projectId, Long userId) {
        return projectRepository
                .findProjectByIdAndUserId(projectId, userId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found or access denied"));
    }
}