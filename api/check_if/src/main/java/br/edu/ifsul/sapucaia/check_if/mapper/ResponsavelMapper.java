package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;

public class ResponsavelMapper {

    public static UsuarioResponse toResponse(Responsavel responsavel) {

        return UsuarioResponse
                .builder()
                .nome(responsavel.getNome())
                .email(responsavel.getEmail())
                .permissao(responsavel.getPermissoes().get(0).getFuncao().toString())
                .build();
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
