package com.luvora.ai.service.impl;

import com.luvora.ai.dto.project.ProjectRequest;
import com.luvora.ai.dto.project.ProjectResponse;
import com.luvora.ai.dto.project.ProjectSummaryResponse;
import com.luvora.ai.entity.Project;
import com.luvora.ai.entity.ProjectMember;
import com.luvora.ai.entity.ProjectMemberId;
import com.luvora.ai.entity.User;
import com.luvora.ai.enums.ProjectRole;
import com.luvora.ai.exception.BadRequestException;
import com.luvora.ai.exception.ResourceNotFoundException;
import com.luvora.ai.mapper.ProjectMapper;
import com.luvora.ai.repository.ProjectMemberRepository;
import com.luvora.ai.repository.ProjectRepository;
import com.luvora.ai.repository.UserRepository;
import com.luvora.ai.security.jwt.JwtUtils;
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
    ProjectMemberRepository projectMemberRepository;
    JwtUtils jwtUtils;

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = jwtUtils.getCurrentUserId();
        List<Project> projects = projectRepository.findAllAccessibleProjectsByUser(userId);
        return projectMapper.toProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long  userId = jwtUtils.getCurrentUserId();
        Project project = getUserProjectOrThrow(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse createProject(ProjectRequest projectRequest) {
        Long userId = jwtUtils.getCurrentUserId();

//        User owner = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

        User owner = userRepository.getReferenceById(userId);

        Project project = Project.builder()
                .name(projectRequest.name())
                .isPublic(false)
                .build();

        projectRepository.save(project);

        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .user(owner)
                .projectRole(ProjectRole.OWNER)
                .invitedAt(Instant.now())
                .acceptedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest projectRequest) {
        Long  userId = jwtUtils.getCurrentUserId();

        Project project = getUserProjectOrThrow(id,  userId);

        project.setName(projectRequest.name());

        project =  projectRepository.save(project);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = jwtUtils.getCurrentUserId();
        Project project = getUserProjectOrThrow(id, userId);
        if (project.getDeletedAt() != null) {
            throw new BadRequestException("Project already deleted");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    // 🔥 Centralized method
    private Project getUserProjectOrThrow(Long projectId,  Long userId) {

        return projectRepository
                .findAccessibleProjectById(projectId, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Project",
                                projectId.toString()
                        ));
    }
}