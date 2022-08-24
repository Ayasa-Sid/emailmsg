package com.example3.mailsent.entity;

import com.example3.mailsent.model.ContentTypee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Email_Table")
@Builder
public class MailEntity {

   @Id
   private String  clientId;
   private String address;
   private String emailTo;
   private String emailFrom;
   private String body;
   private String cc;
   private String bcc;
   private ContentTypee contentType;
}
