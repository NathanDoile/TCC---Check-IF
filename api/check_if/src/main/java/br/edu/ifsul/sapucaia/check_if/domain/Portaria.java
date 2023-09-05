package br.edu.ifsul.sapucaia.check_if.domain;

import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Portaria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private boolean isAtivo;

    @OneToMany(mappedBy = "portaria")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Permissao> permissoes;

}
