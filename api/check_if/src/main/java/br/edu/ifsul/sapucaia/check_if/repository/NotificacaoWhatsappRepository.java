package br.edu.ifsul.sapucaia.check_if.repository;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoWhatsapp;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoWhatsappRepository extends JpaRepository<NotificacaoWhatsapp, Long> {
    NotificacaoWhatsapp findByAlunoAndResponsavel(Aluno aluno, Responsavel responsavel);

    boolean existsByAlunoAndResponsavel(Aluno aluno, Responsavel responsavel);
}
