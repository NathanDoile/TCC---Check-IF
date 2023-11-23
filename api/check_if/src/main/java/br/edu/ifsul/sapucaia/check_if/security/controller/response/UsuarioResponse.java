package br.edu.ifsul.sapucaia.check_if.security.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private String nome;

    private String email;

    private String permissao;

    private String celular;
}
