package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findByNomeContainingOrMatriculaContaining(String nome, String matricula, Pageable pageable);

    Aluno findByChegadasAtrasadas(ChegadaAtrasada chegadaAtrasada);

    Aluno findBySaidasAntecipadas(SaidaAntecipada saidaAntecipada);

    boolean existsByMatricula(String matricula);

    Aluno findByMatricula(String matriculaAluno);

    List<Aluno> findAllByResponsaveis(Responsavel responsavel);
}
