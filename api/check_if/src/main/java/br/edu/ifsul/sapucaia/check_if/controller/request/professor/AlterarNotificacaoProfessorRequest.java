package br.edu.ifsul.sapucaia.check_if.controller.request.professor;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AlterarNotificacaoProfessorRequest {

    @NotNull
    public Long id;

    @NotBlank
    public String tipoNotificacao;
}
