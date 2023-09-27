package br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ConfirmarSaidaAntecipadaRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    public Long id;

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    public boolean saiu;
}
