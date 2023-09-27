package br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CadastrarSaidaAntecipadaRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String nomeResponsavel;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String grauParentesco;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String motivo;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String matriculaAluno;
}
