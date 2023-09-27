package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AlterarNotificacaoResponsavelRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private Long idResponsavel;

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private Long idAluno;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String tipoNotificacao;
}
