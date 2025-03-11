package com.readyup.api.endpoint;

import com.readyup.api.endpointdefinition.PushNotificationEndpointDefinition;
import com.readyup.manager.definitions.PushNotificationManager;
import com.readyup.security.jwt.JwtGenerator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pushNotification")
@CrossOrigin
public class PushNotificationEndpoint implements PushNotificationEndpointDefinition {
//    private final JwtGenerator jwtGenerator;
//    private final PushNotificationManager pushNotificationManager;
//
//    public PushNotificationEndpoint(JwtGenerator jwtGenerator, PushNotificationManager pushNotificationManager) {
//        this.jwtGenerator = jwtGenerator;
//        this.pushNotificationManager = pushNotificationManager;
//    }
//
//    @Override
//    @PostMapping(value = "/setToken")
//    public ResponseEntity setToken(String bearerToken, String pushToken) {
//        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
//        pushNotificationManager.setToken(username, pushToken);
//        return ResponseEntity.ok().build();
//    }
//
//    @Override
//    @GetMapping(value = "/getToken")
//    public ResponseEntity<String> getToken(String bearerToken) {
//        String username = jwtGenerator.getUsernameFromBearer(bearerToken);
//        return ResponseEntity.ok(pushNotificationManager.getToken(username));
//    }


}
