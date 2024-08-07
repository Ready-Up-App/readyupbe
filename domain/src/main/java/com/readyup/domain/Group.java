package com.readyup.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {

    private Long id;
    private String name;
    private LocalDateTime createDtm;
    private String description;
    private List<Person> attendees;

    public Map<String, Object> getProps() {
        Map<String, Object> props = new HashMap<>();
        props.put("name", name);
        props.put("description", description == null ? "" : description);
        props.put("crt_dtm", createDtm == null ? LocalDateTime.now() : createDtm);

        return props;
    }
}
