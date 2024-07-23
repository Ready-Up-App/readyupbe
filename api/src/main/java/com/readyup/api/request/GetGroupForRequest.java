package com.readyup.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class GetGroupForRequest {

    private String personName;
    private Long id;
}
