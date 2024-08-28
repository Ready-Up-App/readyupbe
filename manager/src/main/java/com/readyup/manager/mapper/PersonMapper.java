package com.readyup.manager.mapper;

import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.entity.ReadyStatusEntity;
import com.readyup.ri.relationship.MemberOf;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "createDtm", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
    PersonEntity map(Person person);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
    Person map(PersonEntity personEntity);

    Collection<Person> mapAllEntities(Collection<PersonEntity> entities);
    Collection<PersonEntity> mapAllDomains(Collection<Person> people);

    Person map(MemberOf memberOf);

    @Named("mapReadyStatus")
    default Boolean mapReadyStatus(ReadyStatusEntity status) {

        return status != null && status.getStatus();
    }

    @Named("mapReadyStatus")
    default ReadyStatusEntity mapReadyStatus(Boolean readyStatus) {
        ReadyStatusEntity status = new ReadyStatusEntity();
        status.setStatus(readyStatus);

        return status;
    }
}
