package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoEmailRepository extends JpaRepository<NotificacaoEmail, Long> {
    NotificacaoEmail findByAlunoAndResponsavel(Aluno aluno, Responsavel responsavel);

    boolean existsByAlunoAndResponsavel(Aluno aluno, Responsavel responsavel);
}
