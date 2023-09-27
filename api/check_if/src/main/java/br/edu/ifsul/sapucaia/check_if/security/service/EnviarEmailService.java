package br.edu.ifsul.sapucaia.check_if.security.service;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.EmailRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.security.domain.Enum.StatusEmail.ERRO;
import static br.edu.ifsul.sapucaia.check_if.security.domain.Enum.StatusEmail.SUCESSO;
import static br.edu.ifsul.sapucaia.check_if.security.mapper.EmailMapper.toEntity;
import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Service
public class EnviarEmailService {

    public static final String PATH_ENVIRONMENT_EMAIL = "spring.mail.username";

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Transactional
    public void enviarResponsavel(EnviarEmailRequest request, Responsavel responsavel) {

        Email email = toEntity(request);
        email.setRemetente(responsavel.getNome());
        email.setEmailDe(requireNonNull(env.getProperty(PATH_ENVIRONMENT_EMAIL)));
        email.setEmailPara(responsavel.getEmail());
        email.setEnviadoEm(now());

        enviar(email);
    }

    @Transactional
    public void enviarCriarAdministrador(EnviarEmailRequest request, Administrador administrador) {

        Email email = toEntity(request);
        email.setRemetente(administrador.getNome());
        email.setEmailDe(requireNonNull(env.getProperty(PATH_ENVIRONMENT_EMAIL)));
        email.setEmailPara(administrador.getEmail());
        email.setEnviadoEm(now());

        enviar(email);
    }

    @Transactional
    private void enviar(Email email){

        try{

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailDe());
            message.setTo(email.getEmailPara());
            message.setSubject(email.getTitulo());
            message.setText(email.getMensagem());

            javaMailSender.send(message);

            email.setStatusEmail(SUCESSO);
        }
        catch (MailException me){
            email.setStatusEmail(ERRO);
        }
        finally {
            emailRepository.save(email);
        }
    }
}
