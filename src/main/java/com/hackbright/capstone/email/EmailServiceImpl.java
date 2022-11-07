package com.hackbright.capstone.email;

import com.hackbright.capstone.dtos.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("suneetha.hackbright@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public void sendSimpleMessage(MessageDto messageDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(messageDto.getFrom());
        message.setTo(messageDto.getTo());
        message.setSubject(messageDto.getSubject());
        message.setText(messageDto.getBody());
        emailSender.send(message);

    }

    @Override
    public void sendHtmlMessage(MessageDto messageDto) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(messageDto.getBody(), true);
            helper.setTo(messageDto.getTo());
            helper.setSubject(messageDto.getSubject());
            if (messageDto.getFrom() != null) {
                helper.setFrom(messageDto.getFrom());
            } else {
                helper.setFrom("sunetha.hackbright@gmail.com");
            }
            emailSender.send(mimeMessage);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }
}