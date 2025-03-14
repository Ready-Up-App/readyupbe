package com.readyup.ri.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Container(containerName = "Group")
@Data
public class GroupEntity {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String description;

//    private LocalDateTime createDtm;

    private List<AttendeeEntity> attendees = new ArrayList<>();

//    private ReadyStatusEntity readyStatus;

    public GroupEntity() {}


//    public void addGroupMember(PersonEntity person) {
//        MemberOf rel;
//        if (attendees == null) {
//            attendees = new ArrayList<>();
//            rel = MemberOf.builder().attendee(person).owner(true).build();
//            attendees.add(rel);
//            return;
//        }
//        rel = MemberOf.builder().attendee(person).owner(false).build();
//        attendees.add(rel);
//    }
}
