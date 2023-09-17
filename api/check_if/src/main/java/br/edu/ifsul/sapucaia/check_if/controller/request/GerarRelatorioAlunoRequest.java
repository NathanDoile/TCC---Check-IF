package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GerarRelatorioAlunoRequest {

    @NotNull
    private Long id;

    private int mes;

    @NotNull
    private int ano;
}
