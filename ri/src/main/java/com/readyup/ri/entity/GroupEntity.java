//package com.readyup.ri.entity;
//
//import com.readyup.ri.relationship.MemberOf;
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.data.neo4j.core.schema.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Node("Group")
//@Data
//public class GroupEntity {
//
//    @Id
//    @GeneratedValue
//    private String id;
//
//    @Property("name")
//    private String name;
//
//    @Property("description")
//    private String description;
//
//    @Property("crt_dtm")
//    private LocalDateTime createDtm;
//
//    @Relationship(type = "MEMBER_OF", direction = Relationship.Direction.INCOMING)
//    private List<MemberOf> attendees = new ArrayList<>();
//
//    @Relationship(type = "STATUS", direction = Relationship.Direction.OUTGOING)
//    private ReadyStatusEntity readyStatus;
//
//    public GroupEntity() {}
//
//
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
//}
