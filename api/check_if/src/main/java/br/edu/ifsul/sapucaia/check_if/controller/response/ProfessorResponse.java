package br.edu.ifsul.sapucaia.check_if.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
public class ProfessorResponse {

    private Long id;

    private String nome;

    private String siape;

    private String email;

    private Long celular;

    private boolean notificacaoWhatsapp;

    private boolean notificacaoEmail;
}
