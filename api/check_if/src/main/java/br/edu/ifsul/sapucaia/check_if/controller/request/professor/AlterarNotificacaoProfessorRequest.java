package br.edu.ifsul.sapucaia.check_if.controller.request.professor;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AlterarNotificacaoProfessorRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    public Long id;
}
