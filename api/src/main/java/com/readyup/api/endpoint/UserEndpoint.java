package com.readyup.api.endpoint;

import com.readyup.api.endpointdefinition.UserEndpointDefinition;
import com.readyup.api.request.FriendRequest;
import com.readyup.api.request.RespondFriendRequest;
import com.readyup.api.request.SearchUsernameRequest;
import com.readyup.api.request.SetReadyStatusRequest;
import com.readyup.api.response.GetFriendsResponse;
import com.readyup.api.response.SetReadyStatusResponse;
import com.readyup.domain.SearchedPerson;
import com.readyup.domain.User;
import com.readyup.manager.definitions.UserManager;
import com.readyup.security.jwt.JwtGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin
public class UserEndpoint implements UserEndpointDefinition {

    private final JwtGenerator jwtGenerator;
    private final UserManager userManager;

    public UserEndpoint(JwtGenerator jwtGenerator, UserManager userManager) {
        this.jwtGenerator = jwtGenerator;
        this.userManager = userManager;
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userManager.getAllUsers());
    }


    @Override
    @PostMapping(value = "/friendRequest")
    public ResponseEntity<Boolean> friendRequest(String bearerToken, FriendRequest request) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        if (request.getToUsername() == null || username.equals(request.getToUsername())) {
            return ResponseEntity.badRequest().build();
        }
        Boolean requestSent = userManager.friendRequest(username, request.getToUsername());

        return requestSent ? ResponseEntity.ok(true) : ResponseEntity.unprocessableEntity().body(false);
    }

    @Override
    @PostMapping(value = "/respondFriendRequest")
    public ResponseEntity<Boolean> respondFriendRequest(String bearerToken, RespondFriendRequest request) {
        if (request.getOtherUsername() == null || request.getAccept() == null) {
            return ResponseEntity.badRequest().body(false);
        }

        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        userManager.respondFriendRequest(username, request.getOtherUsername(), request.getAccept());

        return ResponseEntity.ok(true);
    }

    @Override
    @GetMapping(value = "/getFriends")
    public ResponseEntity<GetFriendsResponse> getFriends(String bearerToken) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
        GetFriendsResponse response = GetFriendsResponse.builder()
                .friends(userManager.getFriends(username))
                .build();
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(value = "/searchUsername")
    public ResponseEntity<List<SearchedPerson>> searchUsername(String bearerToken, SearchUsernameRequest request) {
        String requesterUsername = jwtGenerator.getUsernameFromBearer(bearerToken);
        List<SearchedPerson> foundPeople;
        try {
            foundPeople = userManager.searchUsername(requesterUsername, request.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(foundPeople);
    }

    @Override
    @PostMapping(value = "/setReadyStatus")
    public ResponseEntity<SetReadyStatusResponse> setReadyStatus(String bearerToken, SetReadyStatusRequest request) {
        String username = jwtGenerator.getUsernameFromBearer(bearerToken);

        User user = userManager.setReadyStatus(username, request.getStatus());
//        return ResponseEntity.ok(username);
        return ResponseEntity.ok(SetReadyStatusResponse.builder().user(user).build());
    }

}
