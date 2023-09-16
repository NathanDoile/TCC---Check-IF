package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public void porEmail(String email) {

        if(responsavelRepository.existsByEmail(email)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já vinculado a outro usuário.");
        }

    }

    public void porCelular(Long celular) {

        if(responsavelRepository.existsByCelular(celular)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Celular já vinculado a outro usuário.");
        }

    }

}
