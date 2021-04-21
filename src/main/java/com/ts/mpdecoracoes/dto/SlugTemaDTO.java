package com.ts.mpdecoracoes.dto;

import com.ts.mpdecoracoes.entities.SlugTema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlugTemaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String slugTemaNome;
    private Instant dataCriacao;

    public SlugTemaDTO(SlugTema slugTema){
        this.id = slugTema.getId();
        this.slugTemaNome = slugTema.getSlugTemaNome();
        this.dataCriacao = slugTema.getDataCriacao();
    }

}
