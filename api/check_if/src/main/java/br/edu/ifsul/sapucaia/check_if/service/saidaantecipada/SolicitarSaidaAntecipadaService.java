package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.request.SolicitarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.PENDENTE;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toEntity;
import static br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper.toResponse;

@Service
public class SolicitarSaidaAntecipadaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    @Transactional
    public SaidaAntecipadaResponse solicitar(SolicitarSaidaAntecipadaRequest request) {

        validaAlunoService.porId(request.getIdAluno());

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        Aluno aluno = alunoRepository.findById(request.getIdAluno()).get();

        SaidaAntecipada saidaAntecipada = toEntity(request);

        saidaAntecipada.setNomeResponsavel(responsavel.getNome());
        saidaAntecipada.setSituacaoSaida(PENDENTE);
        saidaAntecipada.setResponsavel(responsavel);
        saidaAntecipada.setAluno(aluno);

        saidaAntecipadaRepository.save(saidaAntecipada);

        return toResponse(saidaAntecipada, aluno);
    }
}
