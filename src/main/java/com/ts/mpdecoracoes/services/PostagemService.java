package com.ts.mpdecoracoes.services;

import com.ts.mpdecoracoes.dto.PostagemDTO;
import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.entities.SlugTema;
import com.ts.mpdecoracoes.entities.enums.Categoria;
import com.ts.mpdecoracoes.entities.enums.Modelo;
import com.ts.mpdecoracoes.exceptions.ConteudoNotFoundException;
import com.ts.mpdecoracoes.exceptions.DatabaseException;
import com.ts.mpdecoracoes.repositories.PostagemRepository;
import com.ts.mpdecoracoes.repositories.SlugTemaRepository;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    @Autowired
    private SlugTemaRepository slugTemaRepository;

    @Transactional(readOnly = true)
    public Page<PostagemDTO> findAllPaged(PageRequest pageRequest) {

        Page<Postagem> posts = postagemRepository.findAll(pageRequest);
        return posts.map(post -> new PostagemDTO(post));
    }

    @Transactional(readOnly = true)
    public PostagemDTO findBySlugName(String descricao) {
        Postagem posts = postagemRepository.findBySlug(descricao.toLowerCase());
        if (posts == null) {
            throw new ConteudoNotFoundException("Post não encontrado");
        }
        return new PostagemDTO(posts);
    }

    @Transactional
    public PostagemDTO insert(PostagemDTO dto) {
        Postagem post = new Postagem();
        this.converteDtoParaEntity(dto, post);
        post = postagemRepository.save(post);
        return new PostagemDTO(post);
    }

    @Transactional
    public PostagemDTO update(Long id, PostagemDTO dto) {
        try {
            Postagem post = postagemRepository.getOne(id);
            converteDtoParaEntity(dto, post);
            post = postagemRepository.save(post);
            return new PostagemDTO(post);
        } catch (EntityNotFoundException e ){
            throw new ConteudoNotFoundException("Postagem não encontrado");
        }

    }

    public void delete(Long id) {
        try {
            postagemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ConteudoNotFoundException("Id não encontrado " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }


    public void converteDtoParaEntity(PostagemDTO dto, Postagem post) {
        post.setDescricaoPostagem(dto.getDescricaoPostagem());

        SlugTema slug = slugTemaRepository.getOne(dto.getSlugTema().getId());
        post.setSlugTema(slug);

        post.setUrlImg(dto.getUrlImg());

        if (EnumUtils.isValidEnum(Categoria.class, dto.getCategoria()) && EnumUtils.isValidEnum(Modelo.class, dto.getModelo())) {
            post.setCategoria(dto.getCategoria());
            post.setModelo(dto.getModelo());

        } else {
            throw new ConteudoNotFoundException("Tema ou modelo não encontrado!");
        }

    }
}