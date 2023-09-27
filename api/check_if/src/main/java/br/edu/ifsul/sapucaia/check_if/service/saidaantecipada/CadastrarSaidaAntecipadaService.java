package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.CadastrarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.SAIU;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toEntityFromCadastrarSaidaAntecipada;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toResponse;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class CadastrarSaidaAntecipadaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Transactional
    public SaidaAntecipadaResponse cadastrar(CadastrarSaidaAntecipadaRequest request) {

        validaAlunoService.porMatricula(request.getMatriculaAluno());

        Aluno aluno = alunoRepository.findByMatricula(request.getMatriculaAluno());

        Administrador administrador = usuarioAutenticadoService.getAdministrador();

        SaidaAntecipada saidaAntecipada = toEntityFromCadastrarSaidaAntecipada(request);
        saidaAntecipada.setDataHoraSaida(now());
        saidaAntecipada.setDataHoraAutorizada(saidaAntecipada.getDataHoraSaida());
        saidaAntecipada.setSituacaoSaida(SAIU);
        saidaAntecipada.setAluno(aluno);
        saidaAntecipada.setAdministrador(administrador);

        saidaAntecipadaRepository.save(saidaAntecipada);

        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Saída Antecipada do aluno " + aluno.getNome())
                .mensagem(mensagemSaidaAntecipada(saidaAntecipada))
                .build();

        for(Responsavel responsavel : responsaveis){

            enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
        }

        return toResponse(saidaAntecipada, aluno);
    }

    private String mensagemSaidaAntecipada(SaidaAntecipada saidaAntecipada) {

        return "Prezado(a),\n\n" +
                "Informamos que o aluno " + saidaAntecipada.getAluno().getNome() + " saiu antecipadamente do IFsul no " +
                "dia " + saidaAntecipada.getDataHoraSaida().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                saidaAntecipada.getDataHoraSaida().toLocalTime().format(ofPattern("HH:mm:ss")) + ", com o(a) seu(sua) " +
                saidaAntecipada.getGrauParentesco() + " " + saidaAntecipada.getNomeResponsavel() +
                ".\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }
}
