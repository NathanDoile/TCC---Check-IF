package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AlterarSenhaRequest {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;
}
