package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

    boolean existsByEmail(String email);

    Responsavel findByEmail(String email);
}
