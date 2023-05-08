package com.partyrole.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void triggerEvent(Notification notification) {
    	notificationRepository.save(notification);
    }

    public List<Notification> findByEventType(String eventName) {
    	return notificationRepository.findByEventType(eventName);
    }
}