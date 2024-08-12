package com.readyup.manager;

import com.readyup.manager.definitions.PushNotificationManager;
import com.readyup.ri.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationManagerImpl implements PushNotificationManager {
    private final NotificationRepository notificationRepository;

    public PushNotificationManagerImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void setToken(String username, String pushToken) {
        notificationRepository.createTokenForUsername(username, pushToken);
    }

    @Override
    public String getToken(String username) {
        return notificationRepository.getTokenForUsername(username);
    }

    @Override
    public void sendPushNotification(String username, String message) {
        String pushToken = getToken(username);
        notificationRepository.pushNotification(pushToken, message);
    }
}
