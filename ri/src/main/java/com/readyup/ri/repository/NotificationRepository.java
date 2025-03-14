package com.readyup.ri.repository;

import com.niamedtech.expo.exposerversdk.ExpoPushMessage;
import com.niamedtech.expo.exposerversdk.PushClient;
import com.niamedtech.expo.exposerversdk.PushClientException;
import com.readyup.ri.entity.UserEntity;
import com.readyup.ri.repository.jpa.UserRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NotificationRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    public NotificationRepository(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    public void createTokenForUsername(String username, String token) {

        Optional<UserEntity> foundUser = userRepositoryJpa.findByUsername(username).stream().findFirst();
        if (foundUser.isEmpty()) {
            throw new RuntimeException("ERROR!!");
        }
        foundUser.get().setPushToken(token);
        userRepositoryJpa.save(foundUser.get());
    }

    public String getTokenForUsername(String username) {

        return userRepositoryJpa.getNotifTokenForUsername(username);
    }

    public void pushNotification(String pushToken, String message) {

        try {
            PushClient pushClient = new PushClient();

            ExpoPushMessage expoMessage = new ExpoPushMessage();

            expoMessage.setTo(Collections.singletonList(pushToken));
            expoMessage.setBody(message);
            expoMessage.setData(new HashMap<>());
            pushClient.sendPushNotificationsAsync(Collections.singletonList(expoMessage));

        } catch (PushClientException e) {
            throw new RuntimeException(e);
        }
    }
}
