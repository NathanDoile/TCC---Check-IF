package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String siape;
}
