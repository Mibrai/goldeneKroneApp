package com.rest.goldenekrone.utils;

import com.rest.goldenekrone.userManagement.entities.PassToken;
import com.rest.goldenekrone.userManagement.entities.Status;
import com.rest.goldenekrone.userManagement.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private UUID uuid;

    public void sendMail(User to, String subject, String body){
        uuid = UUID.randomUUID();

        //save uuid in the db
        PassToken passToken = new PassToken();
        passToken.setToken(uuid);
        passToken.setUserId(to.getId());
        passToken.setStatus(Status.ACTIVE);
        passToken.setDatetime(LocalDateTime.now());

        if(passToken.isValid()){

        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        javaMailSender.send(simpleMailMessage);
    }
}
