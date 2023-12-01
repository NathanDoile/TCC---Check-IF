package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

    public void porId(Long idProfessor) {

        if(!professorRepository.existsById(idProfessor)){

            throw new ResponseStatusException(NOT_FOUND, "Professor não encontrado.");
        }
    }

    public void porEmail(String email) {

        if(professorRepository.existsByEmail(email)){

            throw new ResponseStatusException(NOT_FOUND, "E-mail já cadastrado.");
        }
    }


    public void porSiape(String siape) {

        if(professorRepository.existsBySiape(siape)){

            throw new ResponseStatusException(NOT_FOUND, "Siape já cadastrado.");
        }
    }

    public void porCelular(Long celular) {

        if(professorRepository.existsByCelular(celular)){

            throw new ResponseStatusException(NOT_FOUND, "Celular já cadastrado.");
        }
    }
}
