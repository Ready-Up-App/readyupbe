package com.readyup.api.response;

import com.readyup.domain.Person;
import lombok.*;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetReadyStatusResponse {

    private Person user;
}
