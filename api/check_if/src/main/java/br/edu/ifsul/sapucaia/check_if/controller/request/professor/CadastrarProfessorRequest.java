package br.edu.ifsul.sapucaia.check_if.controller.request.professor;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CadastrarProfessorRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String nome;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String siape;

    @NotBlank(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private String email;

    private Long celular;
}
