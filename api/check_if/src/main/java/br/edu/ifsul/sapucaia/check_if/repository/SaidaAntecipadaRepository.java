package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaidaAntecipadaRepository extends JpaRepository<SaidaAntecipada, Long> {

    Page<SaidaAntecipada> findAllByDataHoraAutorizadaBetweenAndSituacaoSaidaOrderByDataHoraAutorizada
            (LocalDateTime inicioData, LocalDateTime fimData, SituacaoSaida situacaoSaida, Pageable pageable);

    List<SaidaAntecipada> findAllByDataHoraSaidaBetweenAndAlunoOrderByDataHoraSaida(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo, Aluno aluno);

    Page<SaidaAntecipada> findAllByAlunoInAndDataHoraSaidaBetweenOrderByDataHoraSaidaDesc(List<Aluno> alunos, LocalDateTime inicioData, LocalDateTime fimData, Pageable pageable);
}
