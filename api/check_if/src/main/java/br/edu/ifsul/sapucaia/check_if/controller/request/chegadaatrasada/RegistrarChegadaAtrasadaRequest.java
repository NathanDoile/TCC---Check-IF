package br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RegistrarChegadaAtrasadaRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String motivo;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String disciplina;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String matriculaAluno;

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private Long idProfessor;
}
