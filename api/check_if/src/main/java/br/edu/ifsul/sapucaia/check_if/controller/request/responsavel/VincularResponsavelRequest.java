package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class VincularResponsavelRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String matricula;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String email;
}
