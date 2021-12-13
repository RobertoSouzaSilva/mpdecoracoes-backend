package com.ts.mpdecoracoes.repositories;

import java.util.List;

import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.utils.Creator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class PostagemRespositoryTeste {

    @Autowired
    private PostagemRepository postagemRepository;
    private PageRequest page;

    @BeforeAll
	void setUp(){
        page =  PageRequest.of(0, 10);
        postagemRepository.save(Creator.postagemParaSalvar());
    }

    @AfterAll
    void setDown(){
        postagemRepository.deleteAll();
    }

    @Test
    public void findShouldReturnPostagemWhenDescricaoPassed(){
        String descricao = "postagem";
        List<Postagem> postagem = postagemRepository.findBySlug(descricao);
        Assertions.assertFalse(postagem.isEmpty());
        Assertions.assertNotNull(postagem);
    }

    @Test
    public void findShouldntReturnPostagemWhenDescricaoPassedDoesntFind(){
        String descricao = "sdfgdfsg";
        List<Postagem> postagem = postagemRepository.findBySlug(descricao);
        Assertions.assertTrue(postagem.isEmpty());
    }

    @Test
    public void pageShouldReturnPagedPostagemWhenMethodCalled(){
        String modelo = "MINI_TABLE";
        String categoria = "INFANTIL";
        String descricao = "postagem";
        Page<Postagem> result = postagemRepository.find(page, categoria, modelo, descricao);
		Assertions.assertFalse(result.isEmpty());
    }
    
}
