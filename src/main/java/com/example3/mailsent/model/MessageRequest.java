package com.example3.mailsent.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.mail.internet.ContentType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    @Email(message = "invalid email format")
    private String emailFrom;
    @Email(message = "invalid email format")
    private String emailTo;
    @NotBlank(message = "body can not be blank")
    private String body;
    private String subject;
    @Enumerated(EnumType.STRING)
    private ContentTypee contentTypee;

}
