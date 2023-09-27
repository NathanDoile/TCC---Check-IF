package br.edu.ifsul.sapucaia.check_if.security.mapper;

import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.domain.Email;

public class EmailMapper {

    public static Email toEntity(EnviarEmailRequest request) {

        return Email
                .builder()
                .titulo(request.getTitulo())
                .mensagem(request.getMensagem())
                .build();
    }
}
