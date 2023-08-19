package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;

public class ResponsavelMapper {

    public static UsuarioResponse toResponse(Responsavel responsavel) {

        return UsuarioResponse
                .builder()
                .nome(responsavel.getNome())
                .email(responsavel.getEmail())
                .build();
    }
}
