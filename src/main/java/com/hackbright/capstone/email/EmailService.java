package com.hackbright.capstone.email;

import com.hackbright.capstone.dtos.MessageDto;
import org.springframework.stereotype.Component;

@Component
public interface EmailService {
    void sendSimpleMessage(
            String to, String subject, String text);

    void sendSimpleMessage(MessageDto messageDto);

    void sendHtmlMessage(MessageDto messageDto);
}
