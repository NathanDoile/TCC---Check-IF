package br.edu.ifsul.sapucaia.check_if.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidarSenhaAtualValidator {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void validar(String senhaAtual, String senha) {

        if(!passwordEncoder.matches(senhaAtual, senha)){

            throw new ResponseStatusException(BAD_REQUEST, "Senha atual incorreta.");
        }
    }
}
