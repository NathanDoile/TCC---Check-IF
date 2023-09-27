package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ChegadaAtrasadaRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
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
public class RegistrarChegadaAtrasadaService {

    @Autowired
    private ChegadaAtrasadaRepository chegadaAtrasadaRepository;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private ValidaProfessorService validaProfessorService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Transactional
    public ChegadaAtrasadaResponse registrar(RegistrarChegadaAtrasadaRequest request) {

        validaAlunoService.porMatricula(request.getMatriculaAluno());
        validaProfessorService.porId(request.getIdProfessor());

        Aluno aluno = alunoRepository.findByMatricula(request.getMatriculaAluno());

        Professor professor = professorRepository.findById(request.getIdProfessor()).get();

        ChegadaAtrasada chegadaAtrasada = toEntity(request);
        chegadaAtrasada.setDataHora(now());
        chegadaAtrasada.setAluno(aluno);
        chegadaAtrasada.setProfessor(professor);

        chegadaAtrasadaRepository.save(chegadaAtrasada);

        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Chegada Atrasada do aluno " + aluno.getNome())
                .mensagem(mensagemChegadaAtrasada(chegadaAtrasada))
                .build();

        for(Responsavel responsavel : responsaveis){

            enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
        }

        return toResponse(chegadaAtrasada);
    }

    private String mensagemChegadaAtrasada(ChegadaAtrasada chegadaAtrasada) {

        return "Prezado(a),\n\n" +
                "Informamos que o aluno " + chegadaAtrasada.getAluno().getNome() + " registrou uma chegada atrasada no " +
                "dia " + chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                chegadaAtrasada.getDataHora().toLocalTime().format(ofPattern("HH:mm:ss")) + ", na disciplina " + chegadaAtrasada.getDisciplina()
                + ".\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }
}
