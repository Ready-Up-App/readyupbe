package com.readyup.api.response;

import com.readyup.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePersonResponse extends BaseResponse{
    private User createdUser;

}
