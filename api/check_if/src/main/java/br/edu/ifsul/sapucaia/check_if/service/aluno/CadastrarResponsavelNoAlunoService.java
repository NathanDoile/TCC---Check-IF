package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class CadastrarResponsavelNoAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Transactional
    public void cadastrar(Responsavel responsavel, Aluno aluno) {

        List<Responsavel> responsaveisPeloAluno = responsavelRepository.findAllByAlunos(aluno);

        if(isNull(responsaveisPeloAluno)){
            responsaveisPeloAluno = new ArrayList<>();
        }

        aluno.setResponsaveis(responsaveisPeloAluno);
        aluno.getResponsaveis().add(responsavel);

        alunoRepository.save(aluno);
    }
}
