package com.readyup.manager.definitions;

public interface PushNotificationManager {

    void setToken(String username, String pushToken);
    String getToken(String username);

    void sendPushNotification(String username, String message);
}
