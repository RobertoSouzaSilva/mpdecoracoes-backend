package com.ts.mpdecoracoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInsertDTO extends UsuarioDTO {
    private static final long serialVersionUID = 1L;

    private String senha;


    
}
