package com.project.springmall.notification.email;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
