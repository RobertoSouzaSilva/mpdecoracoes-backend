package com.ts.mpdecoracoes.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.ts.mpdecoracoes.dto.UsuarioDTO;
import com.ts.mpdecoracoes.dto.UsuarioInsertDTO;
import com.ts.mpdecoracoes.entities.Regra;
import com.ts.mpdecoracoes.entities.Usuario;
import com.ts.mpdecoracoes.exceptions.UsuarioNotFoundException;
import com.ts.mpdecoracoes.repositories.RegraRepository;
import com.ts.mpdecoracoes.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private RegraRepository regraRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	

    @Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Optional<Usuario> userOptional = usuarioRepository.findById(id);			
		Usuario userEntity = userOptional.orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado"));		
		return new UsuarioDTO(userEntity);	
	}
	

	@Transactional
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
		copyDtoToEntity(dto, usuario);
		List<Regra> regra = regraRepository.findAll();
		usuario.setRegras(regra);
		usuario = usuarioRepository.save(usuario);	
		return new UsuarioDTO(usuario);
	}
	

	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
		Usuario usuario = usuarioRepository.getOne(id);
		copyDtoToEntity(dto, usuario);
		List<Regra> regra = regraRepository.findAll();
		usuario.setRegras(regra);
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
		} catch(EntityNotFoundException e ) {
			throw new UsuarioNotFoundException("Id não encontrado " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Violação de integridade");
		}
	}
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario usuario) {
		usuario.setLogin(dto.getLogin());

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(username);
		if(usuario == null) {
			throw new UsuarioNotFoundException("Email não encontrado");
		}
		return usuario;
	}
    
}
