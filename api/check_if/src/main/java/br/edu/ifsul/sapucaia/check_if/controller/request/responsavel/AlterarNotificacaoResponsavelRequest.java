package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class AlterarNotificacaoResponsavelRequest {

    @NotNull
    private Long idResponsavel;

    @NotNull
    private Long idAluno;

    @NotBlank
    private String tipoNotificacao;
}
