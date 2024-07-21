package com.readyup.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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
}
