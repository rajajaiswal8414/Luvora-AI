package com.luvora.ai.controller;

import com.luvora.ai.dto.member.InviteMemberRequest;
import com.luvora.ai.dto.member.MemberResponse;
import com.luvora.ai.dto.member.UpdateMemberRoleRequest;
import com.luvora.ai.entity.ProjectMember;
import com.luvora.ai.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    @GetMapping
    private ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @Valid @RequestBody InviteMemberRequest request
    ) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request, userId));
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @Valid @RequestBody UpdateMemberRoleRequest request
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponse> deleteProjectMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.removeProjectMember(projectId, memberId, userId));
    }

}
