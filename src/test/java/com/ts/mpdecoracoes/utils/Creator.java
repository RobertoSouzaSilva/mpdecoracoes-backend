package com.ts.mpdecoracoes.utils;

import com.ts.mpdecoracoes.dto.PostagemDTO;
import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.entities.SlugTema;

public class Creator {

    public static Postagem postagemParaSalvar(){
        Postagem postagem = new Postagem();
        postagem.setId(1L);
        postagem.setCategoria("INFANTIL");
        postagem.setDescricaoPostagem("Tema mickey");
        postagem.setModelo("MINI_TABLE");
        postagem.setSlugTema(Creator.slugTemaValido());
        postagem.setUrlImg(null);
        return postagem;
    }

    public static PostagemDTO postagemDTOParaSalvar(){
        PostagemDTO postagem = new PostagemDTO();
        postagem.setId(1L);
        postagem.setCategoria("INFANTIL");
        postagem.setDescricaoPostagem("Tema mickey");
        postagem.setModelo("MINI_TABLE");
        postagem.setSlugTema(Creator.slugTemaDTOValido());
        postagem.setUrlImg(null);
        return postagem;
    }

    public static SlugTema slugTemaValido(){
        SlugTema slug = new SlugTema();
        slug.setId(1L);
        slug.setDataCriacao(null);
        slug.setSlugTemaNome("Mickey");
        return slug;
    }

    public static SlugTemaDTO slugTemaDTOValido(){
        SlugTemaDTO slug = new SlugTemaDTO();
        slug.setId(1L);
        slug.setDataCriacao(null);
        slug.setSlugTemaNome("Mickey");
        return slug;
    }
    
}
