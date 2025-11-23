package com.example.actividad2jc.adapters.out.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.actividad2jc.domain.port.out.EmailSenderPort;

@Component
public class LoggingEmailSender implements EmailSenderPort {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String to, String subject, String htmlBody) {
        log.info("=== Simulated email ===\nTo: {}\nSubject: {}\nBody:\n{}", to, subject, htmlBody);
    }
}
