package com.ts.mpdecoracoes.controllers;

import java.net.URI;
import java.util.List;

import com.ts.mpdecoracoes.dto.PostagemDTO;
import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.dto.UriDTO;
import com.ts.mpdecoracoes.services.PostagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
            @RequestParam(value = "description", defaultValue = "") String descricao,
            @RequestParam(value = "orderBy", defaultValue = "descricao_postagem") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<PostagemDTO> posts = postagemService.findAllPaged(pageRequest, categoria, modelo, descricao);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/{descricao}")
    public ResponseEntity<List<PostagemDTO>> findBySlug(@PathVariable String descricao){
        List<PostagemDTO> posts = postagemService.findBySlugName(descricao);
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

    @PostMapping(value = "/image")
    public ResponseEntity<UriDTO> uploadImage(@RequestParam("file") MultipartFile file){
        UriDTO dto = postagemService.uploadFile(file);
        return ResponseEntity.ok().body(dto);
    }

}
