package br.edu.ifsul.sapucaia.check_if.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidarNovaSenhaValidator {

    public void validar(String senhaAtual, String novaSenha) {

        if(senhaAtual.equals(novaSenha)){

            throw new ResponseStatusException(BAD_REQUEST, "A nova senha deve ser diferente da atual.");
        }
    }
}
