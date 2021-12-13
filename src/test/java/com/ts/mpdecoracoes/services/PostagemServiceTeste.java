package com.ts.mpdecoracoes.services;

import java.util.List;

import com.ts.mpdecoracoes.dto.PostagemDTO;
import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.exceptions.ConteudoNotFoundException;
import com.ts.mpdecoracoes.repositories.PostagemRepository;
import com.ts.mpdecoracoes.utils.Creator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class PostagemServiceTeste {

    @InjectMocks
    private PostagemService postagemService;

    @Mock
    private PostagemRepository postagemRepository;

    private String existsSlug;
    private String nonExistsSlug;
    private Postagem postagem;
    private PostagemDTO postagemDTO;
    private List<PostagemDTO> postagens; 
    private PageImpl<Postagem> page;
    private PageRequest pageReq;


    @BeforeEach
	void setUp() throws Exception {
        existsSlug = "mickey";
        nonExistsSlug = "asdf";
        postagem = Creator.postagemParaSalvar();
        page = new PageImpl<>(List.of(postagem));
        pageReq = PageRequest.of(0, 10);
        
        Mockito.when(postagemRepository.find(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(postagemRepository.findBySlug(existsSlug)).thenReturn(List.of(postagem));

    }

    @Test
    public void findAllPagedShouldReturnPage(){
        Page<PostagemDTO> posts = postagemService.findAllPaged(pageReq, "", "", "");
        Assertions.assertNotNull(posts);
        Assertions.assertFalse(posts.isEmpty());
        Mockito.verify(postagemRepository, Mockito.times(1)).find(pageReq, "", "", "");
    }

    @Test
    public void returnListOfPostagensWhenDescricaoExists(){
        List<PostagemDTO> posts = postagemService.findBySlugName(existsSlug);
        Assertions.assertNotNull(posts);
        Assertions.assertEquals(posts.get(0).getSlugTema().getSlugTemaNome().toLowerCase(), existsSlug);
    }

    @Test
    public void throwConteudoExcpetionwhenDescricaoDoesntExists(){
		Assertions.assertThrows(ConteudoNotFoundException.class, () -> {
            postagemService.findBySlugName(nonExistsSlug);
        });

    }

    
}
