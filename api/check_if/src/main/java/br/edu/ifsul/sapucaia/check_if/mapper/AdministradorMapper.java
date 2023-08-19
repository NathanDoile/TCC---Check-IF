package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.UsuarioRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;

public class AdministradorMapper {

    public static UsuarioResponse toResponse(Administrador administrador){

        return UsuarioResponse
                .builder()
                .nome(administrador.getNome())
                .email(administrador.getEmail())
                .build();
    }

    public static Administrador toEntity(UsuarioRequest request) {

        return Administrador
                .builder()
                .senha(request.getSenha())
                .siape(request.getSiape())
                .email(request.getEmail())
                .nome(request.getNome())
                .build();
    }
}
