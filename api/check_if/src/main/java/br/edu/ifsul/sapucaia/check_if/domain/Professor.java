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
public class Professor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private String siape;

    private String email;

    private Long celular;

    private boolean notificacaoWhatsapp;

    private boolean notificacaoEmail;

    private boolean isAtivo;

    @OneToMany(mappedBy = "professor")
    private List<ChegadaAtrasada> chegadasAtrasadas;
}
