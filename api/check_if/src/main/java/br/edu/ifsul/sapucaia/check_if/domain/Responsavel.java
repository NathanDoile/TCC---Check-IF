package br.edu.ifsul.sapucaia.check_if.domain;

import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Responsavel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int celular;

    private String senha;

    private boolean notificacaoWhatsapp;

    private boolean notificacaoEmail;

    private boolean isAtivo;

    @OneToMany(mappedBy = "responsavel")
    private List<SaidaAntecipada> saidasAntecipadas;

    @ManyToMany(mappedBy = "responsaveis")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "responsavel")
    private List<Permissao> permissoes;
}
