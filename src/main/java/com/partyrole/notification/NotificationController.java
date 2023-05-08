package com.partyrole.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
    private NotificationService notificationService;

    @GetMapping("/{eventType}")
    public List<Notification> getAllNotifications(@PathVariable String eventType) {
        return notificationService.findByEventType(eventType);
    }
}