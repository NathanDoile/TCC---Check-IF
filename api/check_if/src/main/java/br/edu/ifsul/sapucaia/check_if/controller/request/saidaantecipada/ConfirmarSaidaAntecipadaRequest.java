package br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ConfirmarSaidaAntecipadaRequest {

    @NotNull
    public Long id;

    @NotNull
    public boolean saiu;
}
