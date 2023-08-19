package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaidaAntecipadaRepository extends JpaRepository<SaidaAntecipada, Long> {
}
