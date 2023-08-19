package br.edu.ifsul.sapucaia.check_if.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String matricula;

    private String turma;

    private LocalDate dataNascimento;

    private boolean isAtivo;

    @OneToMany(mappedBy = "aluno")
    private List<ChegadaAtrasada> chegadasAtrasadas;

    @OneToMany(mappedBy = "aluno")
    private List<SaidaAntecipada> saidasAntecipadas;

    @ManyToMany
    @JoinTable(name = "responsavel_aluno",
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_responsavel"))
    private List<Responsavel> responsaveis;
}
