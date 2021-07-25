package com.ts.mpdecoracoes.controllers;

import com.ts.mpdecoracoes.dto.TokenDTO;
import com.ts.mpdecoracoes.dto.UsuarioInsertDTO;
import com.ts.mpdecoracoes.services.AutenticacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
	public ResponseEntity<TokenDTO> insert(@RequestBody UsuarioInsertDTO dto) {
		TokenDTO token = autenticacaoService.verificaCredenciais(dto.getLogin(), dto.getSenha());
		return ResponseEntity.ok().body(token);
	}
    
}
