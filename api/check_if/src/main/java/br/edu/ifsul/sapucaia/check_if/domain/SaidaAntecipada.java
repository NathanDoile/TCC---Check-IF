package br.edu.ifsul.sapucaia.check_if.domain;

import br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaidaAntecipada {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDateTime dataHoraSaida;

    private LocalDateTime dataHoraAutorizada;

    private String nomeResponsavel;

    @Enumerated(STRING)
    private SituacaoSaida situacaoSaida;

    private String grauParentesco;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;


}
