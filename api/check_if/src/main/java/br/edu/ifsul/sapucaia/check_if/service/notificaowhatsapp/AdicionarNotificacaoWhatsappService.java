package br.edu.ifsul.sapucaia.check_if.service.notificaowhatsapp;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoWhatsapp;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.stereotype.Service;

@Service
public class AdicionarNotificacaoWhatsappService {

    public void adicionar(Responsavel responsavel, Aluno aluno) {

        NotificacaoWhatsapp notificacaoWhatsapp = NotificacaoWhatsapp
                .builder()
                .receber(true)
                .aluno(aluno)
                .build();

        responsavel.adicionarNotificacaoWhatsapp(notificacaoWhatsapp);
    }
}
