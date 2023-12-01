package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.response.AlunoResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoWhatsapp;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.mapper.AlunoMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoWhatsappRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObterPorResponsavelService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Autowired
    private NotificacaoWhatsappRepository notificacaoWhatsappRepository;

    public List<AlunoResponse> obter() {

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        List<Aluno> alunos = alunoRepository.findAllByResponsaveis(responsavel);

        List<AlunoResponse> alunosResponse = alunos
                .stream()
                .map(AlunoMapper::toResponse)
                .toList();

        for(int i = 0; i < alunos.size(); i++){

            Aluno aluno = alunos.get(i);

            NotificacaoWhatsapp notificacaoWhatsapp = notificacaoWhatsappRepository.findByAlunoAndResponsavel(aluno, responsavel);

            alunosResponse.get(i).setNotificacaoWhatsapp(notificacaoWhatsapp.isReceber());

            NotificacaoEmail notificacaoEmail = notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

            alunosResponse.get(i).setNotificacaoEmail(notificacaoEmail.isReceber());
        }

        return alunosResponse;
    }
}
