package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;

public class PortariaMapper {

    public static UsuarioResponse toResponse(Portaria portaria){

        return UsuarioResponse
                .builder()
                .email(portaria.getEmail())
                .build();
    }
}
