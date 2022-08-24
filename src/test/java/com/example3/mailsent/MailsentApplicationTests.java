package com.example3.mailsent;

import com.example3.mailsent.entity.MailEntity;
import com.example3.mailsent.model.ContentTypee;
import com.example3.mailsent.model.MailRequest;
import com.example3.mailsent.model.MailResponse;
import com.example3.mailsent.model.MessageRequest;
import com.example3.mailsent.repository.MailRepository;
import com.example3.mailsent.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MailsentApplicationTests {
    @Autowired
    private MailRepository mailRepository;
    @Autowired
    private MailService mailService;

    @Test
    void contextLoads() {
    }

    @Test
    public void repoTest() {
        List<MailEntity> expectedResult = mailRepository.findAll();
        String email = "ayashasiddika00@gmail.com";
        assertEquals(expectedResult, mailRepository.getDetailByEmail(email));
    }
    @Test
    void sendEmailTest() throws Exception{
        MailResponse mailResponse = new MailResponse(false, "Mail Send successfully");
        MailRequest mailRequest = new MailRequest("", new MessageRequest("ayashasiddika00@gmail.com","gopinathbhowmick425@gmail.com","<h1>this is project using swagger</h1>","spring boot", ContentTypee.HTML),new String[]{},new String[]{});
        assertEquals(mailResponse,mailService.sendEmail(mailRequest));
    }

}


