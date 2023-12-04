package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;

public class ResponsavelMapper {

    public static UsuarioResponse toResponse(Responsavel responsavel) {

        UsuarioResponse usuarioResponse = UsuarioResponse
                .builder()
                .nome(responsavel.getNome())
                .email(responsavel.getEmail())
                .permissao(responsavel.getPermissoes().get(0).getFuncao().toString())
                .build();

        if(responsavel.getCelular() != null){
            usuarioResponse.setCelular(responsavel.getCelular().toString());
        }

        return usuarioResponse;
    }

    public static Responsavel toEntity(CadastrarResponsavelRequest request) {

        return Responsavel
                .builder()
                .nome(request.getNome())
                .celular(request.getCelular())
                .email(request.getEmail())
                .build();
    }
}
