package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AlterarSenhaRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String senhaAtual;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String novaSenha;
}
