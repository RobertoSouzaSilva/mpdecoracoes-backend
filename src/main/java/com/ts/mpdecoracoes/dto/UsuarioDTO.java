package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import com.ts.mpdecoracoes.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String login;

    public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.login = usuario.getLogin();
	}
    
}
