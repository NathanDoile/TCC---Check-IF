package br.edu.ifsul.sapucaia.check_if.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidaMesAnoValidator {


    public void validarMes(int mes) {

        if(mes <= 0 || mes > 12){

            throw new ResponseStatusException(BAD_REQUEST, "O mês inserido é inválido.");
        }
    }

    public void validarAno(int ano) {

        if(ano <= 0){

            throw new ResponseStatusException(BAD_REQUEST, "O ano inserido é inválido.");
        }
    }
}
