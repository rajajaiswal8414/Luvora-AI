package com.luvora.ai.service;

import com.luvora.ai.dto.member.InviteMemberRequest;
import com.luvora.ai.dto.member.MemberResponse;
import com.luvora.ai.dto.member.UpdateMemberRoleRequest;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    MemberResponse removeProjectMember(Long projectId, Long memberId);
}
