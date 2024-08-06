package com.readyup.domain;

import lombok.Data;

@Data
public class SearchedPerson {
    private String username;
    private String firstname;
    private Boolean available;
}
