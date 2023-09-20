package br.edu.ifsul.sapucaia.check_if.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidaTipoNotificacaoValidator {

    public void validar(String tipoNotificacao) {

        if(!tipoNotificacao.equalsIgnoreCase("email") && !tipoNotificacao.equalsIgnoreCase("whatsapp")){

            throw new ResponseStatusException(BAD_REQUEST, "Tipo de notificação inválido, apenas é aceito EMAIL ou WHATSAPP.");
        }
    }
}
