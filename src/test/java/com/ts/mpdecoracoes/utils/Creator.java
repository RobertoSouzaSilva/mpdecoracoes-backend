package com.ts.mpdecoracoes.utils;

import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.entities.SlugTema;

public class Creator {

    public static Postagem postagemParaSalvar(){
        Postagem postagem = new Postagem();
        postagem.setId(1L);
        postagem.setCategoria("INFANTIL");
        postagem.setDescricaoPostagem("postagem teste");
        postagem.setModelo("MINI_TABLE");
        postagem.setSlugTema(Creator.slugTemaValido());
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
    
}
