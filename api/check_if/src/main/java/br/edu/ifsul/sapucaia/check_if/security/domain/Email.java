package br.edu.ifsul.sapucaia.check_if.security.domain;

import br.edu.ifsul.sapucaia.check_if.security.domain.Enum.StatusEmail;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Email {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String remetente;

    private String emailDe;

    private String emailPara;

    private String titulo;

    private String mensagem;

    private LocalDateTime enviadoEm;

    @Enumerated(STRING)
    private StatusEmail statusEmail;
}
