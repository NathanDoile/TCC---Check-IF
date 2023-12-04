package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.VincularResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.NotificacaoEmail;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.NotificacaoEmailRepository;
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
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Autowired
    private ValidaResponsavelService validaResponsavelService;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Transactional
    public void vincular(VincularResponsavelRequest request) {

        validaResponsavelService.naoExisteEmail(request.getEmail());
        validaAlunoService.porMatricula(request.getMatricula());

        Responsavel responsavel = responsavelRepository.findByEmailAndIsAtivo(request.getEmail(), true);
        List<Aluno> alunos = alunoRepository.findAllByResponsaveisAndIsAtivo(responsavel, true);

        Aluno aluno = alunoRepository.findByMatriculaAndIsAtivo(request.getMatricula(), true);
        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunosAndIsAtivo(aluno, true);

        if(!alunos.contains(aluno)){
            alunos.add(aluno);
            responsavel.setAlunos(alunos);

            NotificacaoEmail notificacaoEmail = NotificacaoEmail
                    .builder()
                    .aluno(aluno)
                    .responsavel(responsavel)
                    .receber(true)
                    .build();

            notificacaoEmailRepository.save(notificacaoEmail);
        }

        if(!responsaveis.contains(responsavel)){
            responsaveis.add(responsavel);
            aluno.setResponsaveis(responsaveis);
        }

        alunoRepository.save(aluno);
        responsavelRepository.save(responsavel);
    }
}
