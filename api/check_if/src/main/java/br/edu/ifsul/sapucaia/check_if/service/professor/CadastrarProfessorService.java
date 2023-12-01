package br.edu.ifsul.sapucaia.check_if.service.professor;

import br.edu.ifsul.sapucaia.check_if.controller.request.professor.CadastrarProfessorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.mapper.ProfessorMapper;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.mapper.ProfessorMapper.toEntity;

@Service
public class CadastrarProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ValidaProfessorService validaProfessorService;

    @Transactional
    public void cadastrar(CadastrarProfessorRequest request) {

        validaProfessorService.porEmail(request.getEmail());
        validaProfessorService.porSiape(request.getSiape());

        if(request.getCelular() > 0){
            validaProfessorService.porCelular(request.getCelular());
        }

        Professor professor = toEntity(request);
        professor.setNotificacaoEmail(true);
        professor.setNotificacaoWhatsapp(true);
        professor.setAtivo(true);

        professorRepository.save(professor);
    }
}
