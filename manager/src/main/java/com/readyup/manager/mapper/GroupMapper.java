package com.readyup.manager.mapper;

import com.readyup.domain.Group;
import com.readyup.ri.entity.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    Group entityToDomain(GroupEntity groupEntity);
    GroupEntity domainToEntity(Group groupEntity);
}
