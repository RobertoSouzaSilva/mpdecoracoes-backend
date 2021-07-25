package com.ts.mpdecoracoes.dto;

import java.io.Serializable;

import com.ts.mpdecoracoes.entities.Postagem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostagemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String descricaoPostagem;
    private SlugTemaDTO slugTema;
    private String urlImg;
    private String categoria;
    private String modelo;

    public PostagemDTO(Postagem postagem){
        this.id = postagem.getId();
        this.descricaoPostagem = postagem.getDescricaoPostagem();
        this.slugTema = new SlugTemaDTO(postagem.getSlugTema());
        this.urlImg = postagem.getUrlImg();
        this.categoria = postagem.getCategoria();
        this.modelo = postagem.getModelo();
    }
}
