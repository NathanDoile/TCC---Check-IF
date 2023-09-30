package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarNotificacaoResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoWhatsapp;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoWhatsappRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.notificacaoemail.ValidaNotificacaoEmailService;
import br.edu.ifsul.sapucaia.check_if.service.notificaowhatsapp.ValidaNotificacaoWhatsappService;
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
    private ResponsavelRepository responsavelRepository;

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
    private NotificacaoWhatsappRepository notificacaoWhatsappRepository;

    @Autowired
    private ValidaNotificacaoEmailService validaNotificacaoEmailService;

    @Autowired
    private ValidaNotificacaoWhatsappService validaNotificacaoWhatsappService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public void alterar(AlterarNotificacaoResponsavelRequest request) {

        validaAlunoService.porId(request.getIdAluno());
        validaTipoNotificacaoValidator.validar(request.getTipoNotificacao());

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        Aluno aluno = alunoRepository.findById(request.getIdAluno()).get();

        if(request.getTipoNotificacao().equalsIgnoreCase("EMAIL")){

            validaNotificacaoEmailService.porAlunoEResponsavel(aluno, responsavel);
        }
        else{
            validaNotificacaoWhatsappService.porAlunoEResponsavel(aluno, responsavel);
        }

        if(aluno.idade() < 18){

            if(request.getTipoNotificacao().equalsIgnoreCase("EMAIL")){

                NotificacaoEmail notificacaoEmail =
                        notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

                if(!notificacaoEmail.isReceber()){
                    notificacaoEmail.setReceber(true);

                    notificacaoEmailRepository.save(notificacaoEmail);

                    return;
                }

                NotificacaoWhatsapp notificacaoWhatsapp =
                        notificacaoWhatsappRepository.findByAlunoAndResponsavel(aluno, responsavel);

                if(notificacaoWhatsapp.isReceber()){
                    notificacaoEmail.setReceber(false);

                    notificacaoEmailRepository.save(notificacaoEmail);
                }
                else{
                    throw new ResponseStatusException(UNAUTHORIZED, "O aluno é menor de idade, então deve ter pelo menos um meio de notificação ativo.");
                }

            }
            else{

                NotificacaoWhatsapp notificacaoWhatsapp =
                        notificacaoWhatsappRepository.findByAlunoAndResponsavel(aluno, responsavel);

                if(!notificacaoWhatsapp.isReceber()){
                    notificacaoWhatsapp.setReceber(true);

                    notificacaoWhatsappRepository.save(notificacaoWhatsapp);

                    return;
                }

                NotificacaoEmail notificacaoEmail =
                        notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

                if(notificacaoEmail.isReceber()){
                    notificacaoWhatsapp.setReceber(false);

                    notificacaoWhatsappRepository.save(notificacaoWhatsapp);
                }
                else{
                    throw new ResponseStatusException(UNAUTHORIZED, "O aluno é menor de idade, então deve ter pelo menos um meio de notificação ativo.");
                }
            }

        }
        else{

            if(request.getTipoNotificacao().equalsIgnoreCase("EMAIL")){

                NotificacaoEmail notificacaoEmail =
                        notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

                notificacaoEmail.setReceber(!notificacaoEmail.isReceber());

                notificacaoEmailRepository.save(notificacaoEmail);
            }
            else{

                NotificacaoWhatsapp notificacaoWhatsapp =
                        notificacaoWhatsappRepository.findByAlunoAndResponsavel(aluno, responsavel);

                notificacaoWhatsapp.setReceber(!notificacaoWhatsapp.isReceber());

                notificacaoWhatsappRepository.save(notificacaoWhatsapp);
            }

        }
    }
}
