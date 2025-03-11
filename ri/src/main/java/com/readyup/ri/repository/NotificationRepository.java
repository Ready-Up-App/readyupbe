//package com.readyup.ri.repository;
////
////import com.niamedtech.expo.exposerversdk.ExpoPushMessage;
////import com.niamedtech.expo.exposerversdk.PushClient;
////import com.niamedtech.expo.exposerversdk.PushClientException;
////import com.readyup.ri.entity.NotificationEntity;
////import com.readyup.ri.repository.jpa.NotificationRepositoryJpa;
//import org.springframework.stereotype.Repository;
////
////import java.util.*;
////
//@Repository
//public class NotificationRepository {
////
////    private final NotificationRepositoryJpa notificationRepositoryJpa;
////
////    public NotificationRepository(NotificationRepositoryJpa notificationRepositoryJpa) {
////        this.notificationRepositoryJpa = notificationRepositoryJpa;
////    }
////
////    public void createTokenForUsername(String username, String token) {
////        NotificationEntity notificationEntity = new NotificationEntity();
////        notificationEntity.setPushToken(token);
////
////        notificationRepositoryJpa.createNotificationTokenForUsername(username, notificationEntity.getProps());
////    }
////
////    public String getTokenForUsername(String username) {
////        return notificationRepositoryJpa.getNotificationTokenForUsername(username).getPushToken();
////    }
////
////    public void pushNotification(String pushToken, String message) {
////
////        try {
////            PushClient pushClient = new PushClient();
////
////            ExpoPushMessage expoMessage = new ExpoPushMessage();
////
////            expoMessage.setTo(Collections.singletonList(pushToken));
////            expoMessage.setBody(message);
////            expoMessage.setData(new HashMap<>());
////            pushClient.sendPushNotificationsAsync(Collections.singletonList(expoMessage));
////
////        } catch (PushClientException e) {
////            throw new RuntimeException(e);
////        }
////    }
//}
