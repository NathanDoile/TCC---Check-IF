package br.edu.ifsul.sapucaia.check_if.security.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private String nome;

    private String email;
}
