package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    boolean existsByEmailAndIsAtivo(String email, boolean ativo);

    Administrador findByEmailAndIsAtivo(String email, boolean ativo);

    boolean existsBySiapeAndIsAtivo(String siape, boolean ativo);

    boolean existsByEmailAndIdNotAndIsAtivo(String email, Long id, boolean ativo);
}
