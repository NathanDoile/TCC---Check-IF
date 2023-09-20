package br.edu.ifsul.sapucaia.check_if.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoWhatsapp {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean receber;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;
}
