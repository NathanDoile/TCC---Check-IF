package br.edu.ifsul.sapucaia.check_if.service.notificacaoemail;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaNotificacaoEmailService {

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    public void porAlunoEResponsavel(Aluno aluno, Responsavel responsavel) {

        if(!notificacaoEmailRepository.existsByAlunoAndResponsavel(aluno, responsavel)){

            throw new ResponseStatusException(NOT_FOUND, "Não existe relação entre o aluno e o responsável.");
        }
    }
}
