package com.readyup.api.response;

import com.readyup.domain.User;
import lombok.*;

@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetReadyStatusResponse {

    private User user;
}
