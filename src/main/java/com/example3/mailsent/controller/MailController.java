package com.example3.mailsent.controller;

import com.example3.mailsent.entity.MailEntity;
import com.example3.mailsent.model.MailRequest;
import com.example3.mailsent.model.MailResponse;
import com.example3.mailsent.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v3")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    public MailResponse sendEmail(@Valid @RequestBody MailRequest mailRequest) throws Exception {
        return this.mailService.sendEmail(mailRequest);
    }

    @GetMapping("/getDetails/{emailFrom}")
    public List<MailEntity> getDetailsById(@PathVariable String emailFrom) {
        return this.mailService.getDetailsById(emailFrom);
    }
}
