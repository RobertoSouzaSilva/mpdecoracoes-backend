package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String emailFrom;

    @NotBlank
    @Email
    private String emailTo;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;

    public EmailDTO(com.ts.mpdecoracoes.entities.Email email) {
        this.id = email.getEmailId();
        this.ownerRef = email.getEmailFrom();
        this.emailFrom = email.getEmailFrom();
        this.emailTo = email.getEmailTo();
        this.subject = email.getSubject();
        this.text = email.getText();
    }

    
    
}
