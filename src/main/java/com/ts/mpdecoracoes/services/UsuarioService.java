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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	public UsuarioDTO update(Long id, UsuarioInsertDTO dto) {
		try {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if(!StringUtils.isEmpty(dto.getLogin())){
			usuario.get().setLogin(dto.getLogin());
		} else {
			usuario.get().setLogin(usuario.get().getLogin());
		}
		if(!StringUtils.isEmpty(dto.getNome())){
			usuario.get().setNome(dto.getNome());
		}else {
			usuario.get().setNome(usuario.get().getNome());
		}
		if(!StringUtils.isEmpty(dto.getSenha())){
			usuario.get().setSenha(passwordEncoder.encode(dto.getSenha()));
		}else {
			usuario.get().setSenha(usuario.get().getSenha());
		}
		if(!StringUtils.isEmpty(dto.getUrlImg())){
			usuario.get().setUrlImg(dto.getUrlImg());
		}else {
			usuario.get().setUrlImg(usuario.get().getUrlImg());
		}
		List<Regra> regra = regraRepository.findAll();
		usuario.get().setRegras(regra);
		Usuario usuarioSalvo = usuarioRepository.save(usuario.get());
		return new UsuarioDTO(usuarioSalvo);
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
		usuario.setNome(dto.getNome());
		usuario.setUrlImg(dto.getUrlImg());
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
