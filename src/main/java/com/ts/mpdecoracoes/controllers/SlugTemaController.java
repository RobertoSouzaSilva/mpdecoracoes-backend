package com.ts.mpdecoracoes.controllers;

import com.ts.mpdecoracoes.dto.SlugTemaDTO;
import com.ts.mpdecoracoes.services.SlugTemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/temas")
public class SlugTemaController {

    @Autowired
    private SlugTemaService slugTemaService;

    @GetMapping
    public ResponseEntity<Page<SlugTemaDTO>> findAllPaged(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "slugTemaNome") String orderBy) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<SlugTemaDTO> slugs = slugTemaService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(slugs);
    }

    @GetMapping(value = "/allSlugs")
    public ResponseEntity<List<SlugTemaDTO>> findAll(){
        List<SlugTemaDTO> slugs = slugTemaService.findAll();
        return ResponseEntity.ok().body(slugs);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<SlugTemaDTO> findBySlugTemaName(@PathVariable String name){
        SlugTemaDTO slug = slugTemaService.findBySlugTemaName(name);
        return ResponseEntity.ok().body(slug);
    }

    @PostMapping
    public ResponseEntity<SlugTemaDTO> insert(@RequestBody SlugTemaDTO dto){
        dto = slugTemaService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SlugTemaDTO> update(@RequestBody SlugTemaDTO dto, @PathVariable Long id){
        dto = slugTemaService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SlugTemaDTO> delete(@PathVariable Long id){
        slugTemaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
