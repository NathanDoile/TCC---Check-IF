package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.CadastrarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.SAIU;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toEntityFromCadastrarSaidaAntecipada;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toResponse;
import static java.time.LocalDateTime.now;

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

        return toResponse(saidaAntecipada, aluno);
    }
}
