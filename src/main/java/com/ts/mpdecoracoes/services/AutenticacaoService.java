package com.ts.mpdecoracoes.services;

import java.util.Base64;
import java.util.Optional;

import com.ts.mpdecoracoes.dto.TokenDTO;
import com.ts.mpdecoracoes.entities.Usuario;
import com.ts.mpdecoracoes.exceptions.CredenciaisWrongException;
import com.ts.mpdecoracoes.exceptions.UsuarioNotFoundException;
import com.ts.mpdecoracoes.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TokenDTO verificaCredenciais(String login, String senha){
        Usuario usuarioCredenciado = usuarioRepository.findByLogin(login);
        if(usuarioCredenciado == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado.");
        }
        boolean isPassOk = passwordEncoder.matches(senha, usuarioCredenciado.getSenha());
        TokenDTO token = new TokenDTO();
        if(isPassOk == true){
            token.setToken(toBase64(usuarioCredenciado.getLogin() + ":" + senha));
        } else {
            throw new CredenciaisWrongException("Usuário ou senha incorretos!");
        }
       return token;
    }

    private String toBase64(String dado){
        return Base64.getEncoder().encodeToString(dado.getBytes());
    }
    
}
