package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AlterarSenhaResponsavelRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String senhaAntiga;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String senhaNova;
}
