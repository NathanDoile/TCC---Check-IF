package br.edu.ifsul.sapucaia.check_if.domain;

import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
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

    private boolean isAtivo;

    private String tokenResetarSenha;

    private LocalDateTime dataEnvioToken;

    private Integer tentativasResetarSenha;

    @OneToMany(mappedBy = "responsavel")
    private List<SaidaAntecipada> saidasAntecipadas;

    @ManyToMany(mappedBy = "responsaveis")
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "responsavel", cascade = PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Permissao> permissoes;

    @OneToMany(mappedBy = "responsavel", cascade = PERSIST)
    private List<NotificacaoEmail> notificacoesEmail;

    public void adicionarPermissao(Permissao permissao){
        this.permissoes.add(permissao);
        permissao.setResponsavel(this);
    }

    public void adicionarAluno(Aluno aluno){
        this.alunos.add(aluno);
        aluno.getResponsaveis().add(this);
    }

    public void adicionarNotificaoEmail(NotificacaoEmail notificacaoEmail){
        this.notificacoesEmail.add(notificacaoEmail);
        notificacaoEmail.setResponsavel(this);
    }
}
