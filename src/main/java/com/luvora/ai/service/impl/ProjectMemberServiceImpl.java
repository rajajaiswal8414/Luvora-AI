package com.luvora.ai.service.impl;

import com.luvora.ai.dto.member.InviteMemberRequest;
import com.luvora.ai.dto.member.MemberResponse;
import com.luvora.ai.dto.member.UpdateMemberRoleRequest;
import com.luvora.ai.entity.Project;
import com.luvora.ai.entity.ProjectMember;
import com.luvora.ai.entity.ProjectMemberId;
import com.luvora.ai.entity.User;
import com.luvora.ai.mapper.ProjectMemberMapper;
import com.luvora.ai.repository.ProjectMemberRepository;
import com.luvora.ai.repository.ProjectRepository;
import com.luvora.ai.repository.UserRepository;
import com.luvora.ai.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {

        Project project = getUserProjectOrThrow(projectId, userId);

        List<MemberResponse> memberResponseList = new ArrayList<>();

        // 👑 Add owner
        memberResponseList.add(
                projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner())
        );

        // 👥 Add members
        List<MemberResponse> members = projectMemberRepository
                .findByIdProjectId(projectId)
                .stream()
                .filter(pm -> !pm.getUser().getId().equals(project.getOwner().getId()))
                .map(projectMemberMapper::toProjectMemberResponseFromProjectMember)
                .toList();

        memberResponseList.addAll(members);

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getUserProjectOrThrow(projectId, userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not Owner");
        }

        User invitee = userRepository.findByEmail(request.email()).orElseThrow();

        if(invitee.getId().equals(userId)){
            throw new RuntimeException("Not Invitee");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Project Member already exists");
        }

        ProjectMember pm = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())   // or default to MEMBER
                .acceptedAt(Instant.now())              // invite pending (optional)
                .build();

        projectMemberRepository.save(pm);

        return projectMemberMapper.toProjectMemberResponseFromProjectMember(pm);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId,
                                           UpdateMemberRoleRequest request,
                                           Long userId) {
        Project project = getUserProjectOrThrow(projectId, userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not Owner");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);

        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();

        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromProjectMember(projectMember);
    }

    @Override
    public MemberResponse removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getUserProjectOrThrow(projectId, userId);

        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not Owner");
        }

        if (project.getOwner().getId().equals(memberId)) {
            throw new RuntimeException("Cannot remove project owner");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);

        if(projectMemberRepository.existsById(projectMemberId)){
            throw new RuntimeException("Project Member already exists");
        }

        ProjectMember projectMember = projectMemberRepository
                .findById(projectMemberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // Delete member
        projectMemberRepository.delete(projectMember);

        return projectMemberMapper
                .toProjectMemberResponseFromProjectMember(projectMember);
    }

    private Project getUserProjectOrThrow(Long projectId, Long userId) {
        return projectRepository
                .findProjectByIdAndUserId(projectId, userId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found or access denied"));
    }
}
