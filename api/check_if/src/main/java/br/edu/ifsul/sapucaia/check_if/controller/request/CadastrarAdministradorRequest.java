package br.edu.ifsul.sapucaia.check_if.controller.request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CadastrarAdministradorRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String siape;

    @NotBlank
    @Email
    private String email;


}
