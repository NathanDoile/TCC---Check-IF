package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void porId(Long id){

        if(!alunoRepository.existsById(id)){
            throw new ResponseStatusException(NOT_FOUND, "Aluno não encontrado.");
        }
    }
}
