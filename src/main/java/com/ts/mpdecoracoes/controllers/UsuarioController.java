package com.ts.mpdecoracoes.controllers;

import java.net.URI;

import com.ts.mpdecoracoes.dto.UsuarioDTO;
import com.ts.mpdecoracoes.dto.UsuarioInsertDTO;
import com.ts.mpdecoracoes.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> buscaPeloId(@PathVariable Long id) {
		UsuarioDTO dto = usuarioService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioInsertDTO dto) {
		UsuarioDTO usuarioDto = usuarioService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioInsertDTO dto) {
		UsuarioDTO usuarioDto = usuarioService.update(id, dto);
		return ResponseEntity.ok().body(usuarioDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id){
		usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
    
}
