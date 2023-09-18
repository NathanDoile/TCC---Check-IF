package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class ValidaAdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    public void porEmail(String email) {

        if(administradorRepository.existsByEmail(email)){

            throw new ResponseStatusException(CONFLICT, "E-mail já vinculado a outro usuário.");
        }
    }

    public void porSiape(String siape) {

        if(administradorRepository.existsBySiape(siape)){

            throw new ResponseStatusException(CONFLICT, "SIAPE já vinculado a outro usuário.");
        }
    }
}
