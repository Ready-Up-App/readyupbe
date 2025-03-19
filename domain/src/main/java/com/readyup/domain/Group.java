package com.readyup.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {

    private String id;
    private String name;
    private String description;
    private Boolean isOwner;
    private List<Attendee> attendees;
    private Boolean readyStatus;
    private LocalDateTime createDtm;

//    @JsonIgnore
//    public Map<String, Object> getProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put("name", name);
//        props.put("description", description == null ? "" : description);
//        props.put("crt_dtm", createDtm == null ? LocalDateTime.now() : createDtm);
//
//        return props;
//    }
}
