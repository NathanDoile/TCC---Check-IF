package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.VincularResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VincularResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ValidaResponsavelService validaResponsavelService;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Transactional
    public void vincular(VincularResponsavelRequest request) {

        validaResponsavelService.porEmail(request.getEmail());
        validaAlunoService.porMatricula(request.getMatricula());

        Responsavel responsavel = responsavelRepository.findByEmail(request.getEmail());
        List<Aluno> alunos = alunoRepository.findAllByResponsaveis(responsavel);

        Aluno aluno = alunoRepository.findByMatricula(request.getMatricula());
        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

        alunos.add(aluno);
        responsaveis.add(responsavel);

        alunoRepository.save(aluno);
        responsavelRepository.save(responsavel);
    }
}
