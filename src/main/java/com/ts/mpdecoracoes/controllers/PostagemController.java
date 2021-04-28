package com.ts.mpdecoracoes.controllers;

import com.ts.mpdecoracoes.dto.PostagemDTO;
import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.entities.Postagem;
import com.ts.mpdecoracoes.services.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @GetMapping
    public ResponseEntity<Page<PostagemDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "category", defaultValue = "") String categoria,
            @RequestParam(value = "model", defaultValue = "") String modelo,

            @RequestParam(value = "orderBy", defaultValue = "descricaoPostagem") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostagemDTO> posts = postagemService.findAllPaged(pageRequest, categoria, modelo);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/{descricao}")
    public ResponseEntity<PostagemDTO> findBySlug(@PathVariable String descricao){
        PostagemDTO posts = postagemService.findBySlugName(descricao);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping
    public ResponseEntity<PostagemDTO> insert(@RequestBody PostagemDTO dto){
        dto = postagemService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PostagemDTO> update(@RequestBody PostagemDTO dto, @PathVariable Long id){
        dto = postagemService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SlugTemaDTO> delete(@PathVariable Long id){
        postagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
