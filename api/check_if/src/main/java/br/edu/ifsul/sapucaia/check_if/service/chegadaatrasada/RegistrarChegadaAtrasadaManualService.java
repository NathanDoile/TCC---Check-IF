package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.controller.request.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ChegadaAtrasadaRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toEntity;
import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toResponse;
import static java.time.LocalDateTime.now;

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

    @Transactional
    public ChegadaAtrasadaResponse registrar(RegistrarChegadaAtrasadaRequest request) {

        validaAlunoService.porMatricula(request.getMatriculaAluno());
        validaProfessorService.porId(request.getIdProfessor());

        Aluno aluno = alunoRepository.findByMatricula(request.getMatriculaAluno());
        Professor professor = professorRepository.findById(request.getIdProfessor()).get();
        Administrador administrador = usuarioAutenticadoService.getAdministrador();

        ChegadaAtrasada chegadaAtrasada = toEntity(request);
        chegadaAtrasada.setDataHora(now());
        chegadaAtrasada.setAluno(aluno);
        chegadaAtrasada.setProfessor(professor);
        chegadaAtrasada.setAdministrador(administrador);

        chegadaAtrasadaRepository.save(chegadaAtrasada);

        return toResponse(chegadaAtrasada, aluno);
    }
}
