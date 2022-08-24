package com.example3.mailsent.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {

     @NotBlank(message = "address not null")
     private String address;
     @Valid
     private MessageRequest messageRequest;
     private  String[] cc;
     private  String[] bcc;
}
