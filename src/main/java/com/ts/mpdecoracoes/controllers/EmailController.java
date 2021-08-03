package com.ts.mpdecoracoes.controllers;

import java.net.URI;

import com.ts.mpdecoracoes.dto.EmailDTO;
import com.ts.mpdecoracoes.dto.RecuperaSenhaDTO;
import com.ts.mpdecoracoes.services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/recuperar-senha")
    private ResponseEntity<EmailDTO> recuperarSenha(@RequestBody RecuperaSenhaDTO email){
        EmailDTO emailDTO = emailService.RecuperaSenha(email.getEmail());
        URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(emailDTO.getId())
        .toUri();
        return ResponseEntity.created(uri).body(emailDTO);
    }
}
