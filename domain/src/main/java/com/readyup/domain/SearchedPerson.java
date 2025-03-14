package com.readyup.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchedPerson {
    private String username;
    private String firstname;
    private Boolean available;
}
