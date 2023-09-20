package br.edu.ifsul.sapucaia.check_if.service.notificaowhatsapp;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoWhatsappRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaNotificacaoWhatsappService {

    @Autowired
    private NotificacaoWhatsappRepository notificacaoWhatsappRepository;

    public void porAlunoEResponsavel(Aluno aluno, Responsavel responsavel) {

        if(!notificacaoWhatsappRepository.existsByAlunoAndResponsavel(aluno, responsavel)){

            throw new ResponseStatusException(NOT_FOUND, "Não existe relação entre o aluno e o responsável.");
        }
    }
}
