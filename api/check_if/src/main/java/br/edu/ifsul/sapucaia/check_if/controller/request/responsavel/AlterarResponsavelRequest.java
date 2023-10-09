package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AlterarResponsavelRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String nome;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    @Email
    private String email;

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private Long celular;
}
