package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecuperaSenhaDTO implements Serializable {

    private String email;
    
}
