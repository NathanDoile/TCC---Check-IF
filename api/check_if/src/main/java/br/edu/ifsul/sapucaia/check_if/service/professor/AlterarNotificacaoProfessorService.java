package br.edu.ifsul.sapucaia.check_if.service.professor;

import br.edu.ifsul.sapucaia.check_if.controller.request.professor.AlterarNotificacaoProfessorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaProfessorService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidaTipoNotificacaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarNotificacaoProfessorService {

    @Autowired
    private ValidaProfessorService validaProfessorService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public void alterar(AlterarNotificacaoProfessorRequest request) {

        validaProfessorService.porId(request.getId());

        Professor professor = professorRepository.findById(request.getId()).get();

        professor.setNotificacaoEmail(!professor.isNotificacaoEmail());

        professorRepository.save(professor);
    }
}
