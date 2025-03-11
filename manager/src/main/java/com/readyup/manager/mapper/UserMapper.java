package com.readyup.manager.mapper;


import com.readyup.domain.User;
import com.readyup.ri.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    @Mapping(target = "createDtm", defaultExpression = "java(java.time.LocalDateTime.now())")
//    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
    UserEntity map(User person);
    User map(UserEntity person);
//
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "readyStatus", qualifiedByName = "mapReadyStatus")
//    Person map(UserMapper personEntity);

//    Iterable<Person> mapAllEntities(Collection<UserMapper> entities);
//    Collection<UserMapper> mapAllDomains(Collection<Person> people);

//    Person map(MemberOf memberOf);

//    @Named("mapReadyStatus")
//    default Boolean mapReadyStatus(ReadyStatusEntity status) {
//
//        return status != null && status.getStatus();
//    }
//
//    @Named("mapReadyStatus")
//    default ReadyStatusEntity mapReadyStatus(Boolean readyStatus) {
//        ReadyStatusEntity status = new ReadyStatusEntity();
//        status.setStatus(readyStatus);
//
//        return status;
//    }
}
