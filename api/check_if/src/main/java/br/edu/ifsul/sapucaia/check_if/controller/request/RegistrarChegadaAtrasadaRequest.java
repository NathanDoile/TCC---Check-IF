package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RegistrarChegadaAtrasadaRequest {

    @NotBlank
    private String motivo;

    @NotBlank
    private String disciplina;

    @NotBlank
    private String matriculaAluno;

    @NotNull
    private Long idProfessor;
}
