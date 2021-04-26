package com.ts.mpdecoracoes.dto;

import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.entities.SlugTema;
import com.ts.mpdecoracoes.entities.enums.Categoria;
import com.ts.mpdecoracoes.entities.enums.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

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
