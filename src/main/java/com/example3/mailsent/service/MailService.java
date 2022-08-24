package com.example3.mailsent.service;


import com.example3.mailsent.entity.MailEntity;
import com.example3.mailsent.exception.InValidContentTypeException;
import com.example3.mailsent.exception.InvalidEmailException;
import com.example3.mailsent.model.ContentTypee;
import com.example3.mailsent.model.MailRequest;
import com.example3.mailsent.model.MailResponse;
import com.example3.mailsent.repository.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class MailService {

    @Autowired
    private MailRepository mailRepository;

    public MailResponse sendEmail(MailRequest mailRequest) throws Exception{

        log.info("sendEmail function calling");
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\." + "[a-zA-Z0-9_+&-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        String htmlRegex = "<(“[^”]”|'[^’]’|[^'”>])*>";
        Pattern htmlPattern = Pattern.compile(htmlRegex);
        Pattern pattern = Pattern.compile(emailRegex);
        if (htmlPattern.matcher(mailRequest.getMessageRequest().getBody()).find() && mailRequest.getMessageRequest().getContentTypee() != ContentTypee.HTML) {
            throw new InValidContentTypeException("ContentType Must Be  HTML type");
        }
        if (!htmlPattern.matcher(mailRequest.getMessageRequest().getBody()).find() && mailRequest.getMessageRequest().getContentTypee() != ContentTypee.TEXT) {
            throw new InValidContentTypeException("ContentType Must Be TEXT type");
        }
        for (String ccEmail : mailRequest.getCc()) {
            Matcher matcher = pattern.matcher(ccEmail);
            if (!matcher.find()) {
                throw new InvalidEmailException("cc: " + ccEmail + " invalid cc email format");
            }
        }
        for (String bccEmail : mailRequest.getBcc()) {
            Matcher matcher = pattern.matcher(bccEmail);
            if (!matcher.find()) {
                throw new InvalidEmailException("bcc: " + bccEmail + " invalid bcc email format");
            }
        }
        UUID uuid = UUID.randomUUID();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayashasiddika00@gmail.com", "vnfgygnndtmriyhh");
            }
        });

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(mailRequest.getMessageRequest().getEmailFrom()));
        message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(mailRequest.getMessageRequest().getEmailTo()));
        if (mailRequest.getCc().length > 0) {
            message.setRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(String.join(",", mailRequest.getCc())));
        }
        if (mailRequest.getBcc().length > 0) {
            message.setRecipients(MimeMessage.RecipientType.BCC, InternetAddress.parse(String.join(",", mailRequest.getBcc())));
        }
        message.setSubject(mailRequest.getMessageRequest().getSubject());

        if (mailRequest.getMessageRequest().getContentTypee()==ContentTypee.HTML) {
            message.setContent(mailRequest.getMessageRequest().getBody(), "text/html");
        } else {
            message.setText(mailRequest.getMessageRequest().getBody());
        }
        mailRepository.save(MailEntity
                .builder().clientId(String.valueOf(uuid))
                .address(mailRequest.getAddress())
                .emailFrom(mailRequest.getMessageRequest().getEmailFrom())
                .emailTo(mailRequest.getMessageRequest().getEmailTo())
                .body(mailRequest.getMessageRequest().getBody())
                .contentType(mailRequest.getMessageRequest().getContentTypee())
                .cc(String.join(",", mailRequest.getCc()))
                .bcc(String.join(",", mailRequest.getBcc()))
                .build());
        try {
           Transport.send(message);
            return new MailResponse(false, "Mail Send successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new MailResponse(false, "Mail Not Send successfully");
        }
    }

    public List<MailEntity> getDetailsById(String emailFrom) {
        return this.mailRepository.getDetailByEmail(emailFrom);
    }
}

