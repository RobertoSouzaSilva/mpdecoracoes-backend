package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import com.ts.mpdecoracoes.entities.Regra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegraDTO implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String regra;

    public RegraDTO(Regra entity) {
		id = entity.getId();
		regra = entity.getRegra();
	}
    
}
