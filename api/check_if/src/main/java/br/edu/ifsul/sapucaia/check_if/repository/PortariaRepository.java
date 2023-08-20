package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortariaRepository extends JpaRepository<Portaria, Long> {

    boolean existsByEmail(String email);

    Portaria findByEmail(String email);
}
