package com.ts.mpdecoracoes.repositories;

import java.util.List;

import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.utils.Creator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostagemRespositoryTeste {

    @Autowired
    private PostagemRepository postagemRepository;

    @Test
    public void findShouldReturnPostagemWhenDescricaoPassed(){
        Postagem postagemParaSalvar = postagemRepository.save(Creator.postagemParaSalvar());
        String descricao = "teste";
        List<Postagem> postagem = postagemRepository.findBySlug(descricao);
        Assertions.assertFalse(postagem.isEmpty());
        Assertions.assertNotNull(postagem);
    }
    
}
