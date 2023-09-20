package br.edu.ifsul.sapucaia.check_if.service.notificacaoemail;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.stereotype.Service;

@Service
public class AdicionarNotificacaoEmailService {

    public void adicionar(Responsavel responsavel, Aluno aluno) {

        NotificacaoEmail notificacaoEmail = NotificacaoEmail
                .builder()
                .receber(true)
                .aluno(aluno)
                .build();

        responsavel.adicionarNotificaoEmail(notificacaoEmail);
    }
}
