package com.ts.mpdecoracoes.repositories;

import com.ts.mpdecoracoes.entities.SlugTema;
import com.ts.mpdecoracoes.utils.Creator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
public class SlugTemaRepositoryTeste {

    @Autowired
    private SlugTemaRepository slugTemaRepository;


    @BeforeAll
	void setUp(){
        slugTemaRepository.save(Creator.slugTemaValido());
    }

    @Test
    public void findShouldReturnWhenSlugTemaNomeIsPassed(){
        String nome = "mickey";
        SlugTema slug = slugTemaRepository.findByTemaNome(nome);
        Assertions.assertEquals(slug.getSlugTemaNome(), "Mickey");
        Assertions.assertNotNull(slug);
    }

    @Test
    public void findShouldntReturnWhenSlugTemaDoesntExist(){
        String nome = "asdf";
        SlugTema slug = slugTemaRepository.findByTemaNome(nome);
        Assertions.assertNull(slug);
    }
    
}
