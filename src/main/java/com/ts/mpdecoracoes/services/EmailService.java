package com.ts.mpdecoracoes.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.ts.mpdecoracoes.dto.EmailDTO;
import com.ts.mpdecoracoes.dto.UsuarioDTO;
import com.ts.mpdecoracoes.entities.Email;
import com.ts.mpdecoracoes.entities.Regra;
import com.ts.mpdecoracoes.entities.Usuario;
import com.ts.mpdecoracoes.entities.enums.StatusEmail;
import com.ts.mpdecoracoes.exceptions.UsuarioNotFoundException;
import com.ts.mpdecoracoes.repositories.EmailRepository;
import com.ts.mpdecoracoes.repositories.RegraRepository;
import com.ts.mpdecoracoes.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
	private RegraRepository regraRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetenteV;

    @Transactional
    public EmailDTO RecuperaSenha(String email) {
        Usuario usuario = usuarioRepository.findByLogin(email);
        if(usuario == null){
            throw new UsuarioNotFoundException("Usuário não encontrado!");
        }
        Random r = new Random();
        Integer senhaProvisoria = r.nextInt(999999) + 100000;
        UsuarioDTO dto = new UsuarioDTO(usuario);
        update(usuario.getId(), dto, senhaProvisoria.toString());

        Email emailParaSalvar = new Email();
        String remetente = remetenteV;
        String assunto = "Nova senha provisória! :)";
        String criador = "Equipe MPDecorações";
        String senhaEnviada = String.format("Sua senha provisória é: %s. Ao entrar na plataforma, troque a sua senha imediatamente.", senhaProvisoria.toString());
        String senhaParaTabela = String.format("Sua senha provisória é: %s. Ao entrar na plataforma, troque a sua senha imediatamente.", passwordEncoder.encode(senhaProvisoria.toString()));

            
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(email);
            message.setSubject(assunto);
            message.setText(senhaEnviada);
            javaMailSender.send(message);

            emailParaSalvar.setEmailFrom(email);
            emailParaSalvar.setEmailTo(remetente);
            emailParaSalvar.setOwnerRef(criador);
            emailParaSalvar.setSendDateEmail(LocalDateTime.now());
            emailParaSalvar.setStatusEmail(StatusEmail.ERROR);
            emailParaSalvar.setSubject(assunto);
            emailParaSalvar.setText(senhaParaTabela);

        } catch (MailException e){
            emailParaSalvar.setStatusEmail(StatusEmail.ERROR);
        } finally {
            emailRepository.save(emailParaSalvar);
            return new EmailDTO(emailParaSalvar);
        }

    }

    @Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto, String senhaProvisoria) {
		try {
		Usuario usuario = usuarioRepository.getOne(id);
        usuario.setSenha(passwordEncoder.encode(senhaProvisoria));
		copyDtoToEntity(dto, usuario);
		List<Regra> regra = regraRepository.findAll();
		usuario.setRegras(regra);
		usuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(usuario);
		} catch(EntityNotFoundException e ) {
			throw new UsuarioNotFoundException("Id não encontrado " + id);
		}
	}

    private void copyDtoToEntity(UsuarioDTO dto, Usuario usuario) {
		usuario.setLogin(dto.getLogin());

	}

    
}
