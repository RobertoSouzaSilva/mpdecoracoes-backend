package com.ts.mpdecoracoes.services;

import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.entities.SlugTema;
import com.ts.mpdecoracoes.exceptions.DatabaseException;
import com.ts.mpdecoracoes.exceptions.ConteudoNotFoundException;
import com.ts.mpdecoracoes.repositories.SlugTemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class SlugTemaService {

    @Autowired
    private SlugTemaRepository slugTemaRepository;

    @Transactional(readOnly = true)
    public Page<SlugTemaDTO> findAllPaged(PageRequest pageRequest) {

        Page<SlugTema> slugs = slugTemaRepository.findAll(pageRequest);
        return slugs.map(slug -> new SlugTemaDTO(slug));
    }

    public List<SlugTemaDTO> findAll() {
        List<SlugTema> slugs = slugTemaRepository.findAll();
        return slugs.stream().map(slug -> new SlugTemaDTO(slug)).collect(Collectors.toList());
    }



    @Transactional(readOnly = true)
    public SlugTemaDTO findBySlugTemaName(String name) {

        SlugTema slug = slugTemaRepository.findByTemaNome(name.toLowerCase());
        if(slug == null) {
            throw new ConteudoNotFoundException("Tema não encontrado");
        }
        return new SlugTemaDTO(slug);
    }

    @Transactional
    public SlugTemaDTO insert(SlugTemaDTO dto) {
        SlugTema slug = new SlugTema();
        this.converteDtoParaEntity(dto, slug);
        slug = slugTemaRepository.save(slug);
        return new SlugTemaDTO(slug);
    }

    @Transactional
    public SlugTemaDTO update(Long id, SlugTemaDTO dto) {
        try {
            SlugTema slug = slugTemaRepository.getOne(id);
            converteDtoParaEntity(dto, slug);
            slug = slugTemaRepository.save(slug);
            return new SlugTemaDTO(slug);
        } catch (EntityNotFoundException e ){
            throw new ConteudoNotFoundException("Tema não encontrado");
        }

    }

    public void delete(Long id) {
        try {
            slugTemaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ConteudoNotFoundException("Id não encontrado " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade");
        }
    }

    private void converteDtoParaEntity(SlugTemaDTO dto, SlugTema slug){
         slug.setSlugTemaNome(dto.getSlugTemaNome());
         slug.setDataCriacao(dto.getDataCriacao());
    }

  

}
