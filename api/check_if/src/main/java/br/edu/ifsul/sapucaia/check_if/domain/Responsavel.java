package br.edu.ifsul.sapucaia.check_if.domain;

import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Responsavel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private Long celular;

    private String senha;

    private boolean notificacaoWhatsapp;

    private boolean notificacaoEmail;

    private boolean isAtivo;

    @OneToMany(mappedBy = "responsavel")
    private List<SaidaAntecipada> saidasAntecipadas;

    @ManyToMany(mappedBy = "responsaveis")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "responsavel")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Permissao> permissoes;
}
