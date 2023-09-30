package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.ConfirmarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaSaidaAntecipadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.NÃO_COMPARECEU;
import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.SAIU;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class ConfirmarSaidaAntecipadaService {

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    @Autowired
    private ValidaSaidaAntecipadaService validaSaidaAntecipadaService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Transactional
    public void confirmar(ConfirmarSaidaAntecipadaRequest request) {

        validaSaidaAntecipadaService.porid(request.getId());

        SaidaAntecipada saidaAntecipada = saidaAntecipadaRepository.findById(request.getId()).get();

        validaSaidaAntecipadaService.pendente(saidaAntecipada.getSituacaoSaida());

        if(request.isSaiu()){
            saidaAntecipada.setSituacaoSaida(SAIU);

            saidaAntecipada.setDataHoraSaida(now());
        }
        else{
            saidaAntecipada.setSituacaoSaida(NÃO_COMPARECEU);
        }

        saidaAntecipadaRepository.save(saidaAntecipada);

        if(saidaAntecipada.getSituacaoSaida() == SAIU){

            Aluno aluno = alunoRepository.findBySaidasAntecipadas(saidaAntecipada);

            List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

            EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                    .builder()
                    .titulo("Saída Antecipada do aluno " + aluno.getNome())
                    .mensagem(mensagemSaidaAntecipada(saidaAntecipada, aluno))
                    .build();

            for(Responsavel responsavel : responsaveis){

                NotificacaoEmail notificacaoEmail = notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

                if(notificacaoEmail.isReceber()){

                    enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
                }
            }
        }
    }

    private String mensagemSaidaAntecipada(SaidaAntecipada saidaAntecipada, Aluno aluno) {

        return "Prezado(a),\n\n" +
                "Informamos que o aluno " + aluno.getNome() + " saiu antecipadamente do IFsul no " +
                "dia " + saidaAntecipada.getDataHoraSaida().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                saidaAntecipada.getDataHoraSaida().toLocalTime().format(ofPattern("HH:mm:ss")) + ".\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }
}
