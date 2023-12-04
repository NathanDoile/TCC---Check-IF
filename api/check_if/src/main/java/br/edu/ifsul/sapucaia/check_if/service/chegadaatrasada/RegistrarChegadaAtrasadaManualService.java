package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.*;
import br.edu.ifsul.sapucaia.check_if.repository.*;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toEntity;
import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toResponse;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class RegistrarChegadaAtrasadaManualService {

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private ValidaProfessorService validaProfessorService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ChegadaAtrasadaRepository chegadaAtrasadaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Transactional
    public ChegadaAtrasadaResponse registrar(RegistrarChegadaAtrasadaRequest request) {

        validaAlunoService.porMatricula(request.getMatriculaAluno());
        validaProfessorService.porId(request.getIdProfessor());

        Aluno aluno = alunoRepository.findByMatriculaAndIsAtivo(request.getMatriculaAluno(), true);
        Professor professor = professorRepository.findById(request.getIdProfessor()).get();
        Administrador administrador = usuarioAutenticadoService.getAdministrador();

        ChegadaAtrasada chegadaAtrasada = toEntity(request);
        chegadaAtrasada.setDataHora(now());
        chegadaAtrasada.setAluno(aluno);
        chegadaAtrasada.setProfessor(professor);
        chegadaAtrasada.setAdministrador(administrador);

        chegadaAtrasadaRepository.save(chegadaAtrasada);

        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunosAndIsAtivo(aluno, true);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Chegada Atrasada da(o) aluna(o) " + aluno.getNome())
                .mensagem(mensagemChegadaAtrasada(chegadaAtrasada))
                .build();

        for(Responsavel responsavel : responsaveis){

            NotificacaoEmail notificacaoEmail = notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

            if(notificacaoEmail.isReceber()){

                enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
            }
        }

        if(professor.isNotificacaoEmail()){

            EnviarEmailRequest enviarEmailRequestProfessor = EnviarEmailRequest
                    .builder()
                    .titulo("Chegada Atrasada do(a) aluno(a) " + aluno.getNome())
                    .mensagem(mensagemChegadaAtrasadaProfessor(chegadaAtrasada))
                    .build();

            enviarEmailService.enviarProfessor(enviarEmailRequestProfessor, professor);
        }

        return toResponse(chegadaAtrasada);
    }

    private String mensagemChegadaAtrasada(ChegadaAtrasada chegadaAtrasada) {

        return "Prezada(o),\n\n" +
                "Informamos que a(o) aluna(o) " + chegadaAtrasada.getAluno().getNome() + " registrou uma chegada atrasada no " +
                "dia " + chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                chegadaAtrasada.getDataHora().toLocalTime().format(ofPattern("HH:mm:ss")) + ", na disciplina " + chegadaAtrasada.getDisciplina()
                + " e alegou o seguinte motivo: '" + chegadaAtrasada.getMotivo() + "'.\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }

    private String mensagemChegadaAtrasadaProfessor(ChegadaAtrasada chegadaAtrasada) {

        return "Prezado(a),\n\n" +
                "Informamos que o(a) aluno(a) " + chegadaAtrasada.getAluno().getNome() + ", da turma " + chegadaAtrasada.getAluno().getTurma() + ", " +
                "registrou uma chegada atrasada no " +
                "dia " + chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                chegadaAtrasada.getDataHora().toLocalTime().format(ofPattern("HH:mm:ss")) + ", na disciplina " + chegadaAtrasada.getDisciplina()
                + " e alegou o seguinte motivo: '" + chegadaAtrasada.getMotivo() + "'.\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }
}
