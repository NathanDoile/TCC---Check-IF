package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class ValidaResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public void porEmail(String email) {

        if(responsavelRepository.existsByEmailAndIsAtivo(email, true)){

            throw new ResponseStatusException(CONFLICT, "E-mail já vinculado a outro usuário.");
        }

    }

    public void porCelular(Long celular) {

        if(responsavelRepository.existsByCelularAndIsAtivo(celular, true)){

            throw new ResponseStatusException(CONFLICT, "Celular já vinculado a outro usuário.");
        }

    }

    public void naoExisteEmail(String email) {

        if(!responsavelRepository.existsByEmailAndIsAtivo(email, true)){

            throw new ResponseStatusException(CONFLICT, "E-mail não encontrado.");
        }
    }
}
