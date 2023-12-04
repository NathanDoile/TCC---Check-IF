package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ObterSaidasAntecipadasResponsavelService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    public Page<SaidaAntecipadaResponse> obter(String data, Pageable pageable) {

        LocalDate date = LocalDate.parse(data);

        LocalDateTime inicioData = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime fimData = LocalDateTime.of(date, LocalTime.MAX);

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        List<Aluno> alunos = alunoRepository.findAllByResponsaveisAndIsAtivo(responsavel, true);

        Page<SaidaAntecipada> saidasAntecipadas = saidaAntecipadaRepository
                .findAllByAlunoInAndDataHoraSaidaBetweenOrderByDataHoraSaidaDesc(alunos, inicioData, fimData, pageable);

        return saidasAntecipadas.map(SaidaAntecipadaMapper::toResponse);
    }
}
