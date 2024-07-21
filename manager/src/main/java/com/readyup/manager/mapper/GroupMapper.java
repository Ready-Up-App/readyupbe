package com.readyup.manager.mapper;

import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.relationship.MemberOf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "createDtm", defaultExpression = "java(java.time.LocalDateTime.now())")
    GroupEntity map(Group group);

    @Mapping(target = "attendees", qualifiedByName = "mapMembersToPersons")
    Group map(GroupEntity groupEntity);

    Collection<Group> mapAllEntities(Collection<GroupEntity> entities);
    Collection<GroupEntity> mapAllDomains(Collection<Group> groups);

    @Named("mapMembersToPersons")
    default List<Person> mapMembersToPersons(List<MemberOf> members) {
        return members.stream()
                .map((member) -> {
                    return PersonMapper.INSTANCE.map(member.getAttendee());
                }).toList();
    }


}
