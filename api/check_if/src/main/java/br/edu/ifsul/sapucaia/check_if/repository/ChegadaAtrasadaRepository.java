package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChegadaAtrasadaRepository extends JpaRepository<ChegadaAtrasada, Long> {
    Page<ChegadaAtrasada> findByDataHoraBetweenOrderByDataHoraDesc(LocalDateTime inicioData, LocalDateTime fimData, Pageable pageable);

    List<ChegadaAtrasada> findAllByDataHoraBetweenAndAlunoOrderByDataHora(LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo, Aluno aluno);
}
