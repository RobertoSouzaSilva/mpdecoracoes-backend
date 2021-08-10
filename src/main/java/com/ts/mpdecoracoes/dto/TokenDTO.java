package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable {

    private Long id;
    private String token;
    private String nome;
    private String email;
    private String urlImg;
    
}
