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
public class Administrador {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String siape;

    private String email;

    private String senha;

    private boolean isAtivo;

    @OneToMany(mappedBy = "administrador")
    private List<ChegadaAtrasada> chegadasAtrasadas;

    @OneToMany(mappedBy = "administrador")
    private List<SaidaAntecipada> saidasAntecipadas;

    @OneToMany(mappedBy = "administrador")
    private List<Permissao> permissoes;
}
