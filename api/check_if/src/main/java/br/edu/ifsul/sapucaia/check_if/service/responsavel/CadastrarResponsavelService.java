package br.edu.ifsul.sapucaia.check_if.service.responsavel;


import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.PermissaoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import br.edu.ifsul.sapucaia.check_if.service.aluno.CadastrarResponsavelNoAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.permissao.CadastrarPermissaoResponsavelService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static br.edu.ifsul.sapucaia.check_if.mapper.ResponsavelMapper.toEntity;
import static java.util.List.of;

@Service
public class CadastrarResponsavelService {

    @Autowired
    private ValidaResponsavelService validarResponsavelService;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CadastrarPermissaoResponsavelService cadastrarPermissaoResponsavelService;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private CadastrarResponsavelNoAlunoService cadastrarResponsavelNoAlunoService;


    @Transactional
    public void cadastrar(CadastrarResponsavelRequest request) {

        validarResponsavelService.porEmail(request.getEmail());

        validarResponsavelService.porCelular(request.getCelular());

        for(Long id : request.getIdAlunos()){

            validaAlunoService.porId(id);
        }

        Permissao permissao = cadastrarPermissaoResponsavelService.cadastrar();

        Responsavel responsavel = toEntity(request);
        responsavel.setSenha(passwordEncoder.encode(responsavel.getCelular().toString()));
        responsavel.setAtivo(true);
        responsavel.setNotificacaoEmail(true);
        responsavel.setNotificacaoWhatsapp(true);
        responsavel.setAlunos(new ArrayList<>());
        responsavel.setPermissoes(of(permissao));

        for(Long id : request.getIdAlunos()){

            Aluno aluno = alunoRepository.findById(id).get();

            responsavel.getAlunos().add(aluno);
        }

        permissao.setResponsavel(responsavel);

        responsavelRepository.save(responsavel);

        for(Long id : request.getIdAlunos()){

            Aluno aluno = alunoRepository.findById(id).get();

            cadastrarResponsavelNoAlunoService.cadastrar(responsavel, aluno);
        }

        permissaoRepository.save(permissao);
    }
}
