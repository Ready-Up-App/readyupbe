package com.readyup.manager.mapper;

import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person entityToDomain(PersonEntity personEntity);
    PersonEntity domainToEntity(Person person);
}
