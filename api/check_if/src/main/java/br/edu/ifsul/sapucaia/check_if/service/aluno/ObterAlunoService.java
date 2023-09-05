package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.response.AlunoResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.mapper.AlunoMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.sapucaia.check_if.mapper.AlunoMapper.toResponse;

@Service
public class ObterAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    public AlunoResponse obter(Long id){

        validaAlunoService.porId(id);

        Aluno aluno = alunoRepository.findById(id).get();

        return toResponse(aluno);
    }
}
