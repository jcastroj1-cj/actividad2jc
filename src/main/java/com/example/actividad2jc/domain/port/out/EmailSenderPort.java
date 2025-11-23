package com.example.actividad2jc.domain.port.out;

public interface EmailSenderPort {
    void send(String to, String subject, String htmlBody);
}
