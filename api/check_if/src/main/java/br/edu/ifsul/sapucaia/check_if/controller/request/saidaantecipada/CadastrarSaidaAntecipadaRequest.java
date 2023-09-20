package br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CadastrarSaidaAntecipadaRequest {

    @NotBlank
    private String nomeResponsavel;

    @NotBlank
    private String grauParentesco;

    @NotBlank
    private String motivo;

    @NotBlank
    private String matriculaAluno;
}
