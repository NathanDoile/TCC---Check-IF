package br.edu.ifsul.sapucaia.check_if.controller.request.aluno;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GerarRelatorioAlunoRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private Long id;

    private int mes;

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private int ano;
}
