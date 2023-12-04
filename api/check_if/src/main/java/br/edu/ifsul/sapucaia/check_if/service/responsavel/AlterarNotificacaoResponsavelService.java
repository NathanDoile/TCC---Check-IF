package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarNotificacaoResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.notificacaoemail.ValidaNotificacaoEmailService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidaTipoNotificacaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
public class AlterarNotificacaoResponsavelService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private ValidaResponsavelService validaResponsavelService;

    @Autowired
    private ValidaTipoNotificacaoValidator validaTipoNotificacaoValidator;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Autowired
    private ValidaNotificacaoEmailService validaNotificacaoEmailService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public void alterar(AlterarNotificacaoResponsavelRequest request) {

        validaAlunoService.porId(request.getIdAluno());

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        Aluno aluno = alunoRepository.findById(request.getIdAluno()).get();

        validaNotificacaoEmailService.porAlunoEResponsavel(aluno, responsavel);

        NotificacaoEmail notificacaoEmail = notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

        if(aluno.idade() >= 18){

            notificacaoEmail.setReceber(!notificacaoEmail.isReceber());

            notificacaoEmailRepository.save(notificacaoEmail);

        }
        else{

            if(notificacaoEmail.isReceber()){
                throw new ResponseStatusException(UNAUTHORIZED, "Não é possível desativar notificações de alunos menores de idade.");
            }

        }
    }
}
