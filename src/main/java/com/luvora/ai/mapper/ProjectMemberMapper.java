package com.luvora.ai.mapper;

import com.luvora.ai.dto.member.MemberResponse;
import com.luvora.ai.entity.Project;
import com.luvora.ai.entity.ProjectMember;
import com.luvora.ai.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User user);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toProjectMemberResponseFromProjectMember(ProjectMember projectMember);
}
