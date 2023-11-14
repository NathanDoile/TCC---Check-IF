package br.edu.ifsul.sapucaia.check_if.service.professor;

import br.edu.ifsul.sapucaia.check_if.controller.response.ProfessorResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ifsul.sapucaia.check_if.mapper.ProfessorMapper;

import java.util.List;

@Service
public class BuscarTodosProfessorService {


    @Autowired
    private ProfessorRepository professorRepository;


    public List<ProfessorResponse> buscar() {

        List<Professor> professores = professorRepository.findAll();

        return professores
                .stream()
                .map(ProfessorMapper::toResponse)
                .toList();
    }
}
