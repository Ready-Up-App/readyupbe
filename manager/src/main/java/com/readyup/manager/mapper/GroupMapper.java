package com.readyup.manager.mapper;

import com.readyup.domain.Group;
import com.readyup.domain.User;
import com.readyup.ri.entity.GroupEntity;
//import com.readyup.ri.entity.ReadyStatusEntity;
//import com.readyup.ri.relationship.MemberOf;
import com.readyup.ri.entity.UserGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

//    @Mapping(target = "createDtm", defaultExpression = "java(java.time.LocalDateTime.now())")
//    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
    GroupEntity map(Group group);

//    @Mapping(target = "attendees", qualifiedByName = "mapMembersToPersons")
//    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
    Group map(GroupEntity groupEntity);

    Collection<Group> mapAllEntities(Collection<GroupEntity> entities);
    Collection<GroupEntity> mapAllDomains(Collection<Group> groups);

    UserGroupEntity mapToUserGroup(GroupEntity groupEntity);
}
