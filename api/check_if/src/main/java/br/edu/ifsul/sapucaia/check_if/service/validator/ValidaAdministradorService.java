package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaAdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public void porEmail(String email) {

        if(administradorRepository.existsByEmail(email)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail já vinculado a outro usuário.");
        }
    }

    public void porSiape(String siape) {

        if(administradorRepository.existsBySiape(siape)){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SIAPE já vinculado a outro usuário.");
        }
    }
}
