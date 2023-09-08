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

            throw new ResponseStatusException(NOT_FOUND, "Professor não exncontrado.");
        }
    }
}
