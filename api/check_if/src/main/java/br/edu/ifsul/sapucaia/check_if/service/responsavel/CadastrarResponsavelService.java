package br.edu.ifsul.sapucaia.check_if.service.responsavel;


import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.service.notificacaoemail.AdicionarNotificacaoEmailService;
import br.edu.ifsul.sapucaia.check_if.service.notificaowhatsapp.AdicionarNotificacaoWhatsappService;
import br.edu.ifsul.sapucaia.check_if.service.permissao.AdicinarPermissaoResponsavelService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.edu.ifsul.sapucaia.check_if.mapper.ResponsavelMapper.toEntity;

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
    private AdicinarPermissaoResponsavelService adicionarPermissaoResponsavelService;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private AdicionarNotificacaoEmailService adicionarNotificacaoEmailService;

    @Autowired
    private AdicionarNotificacaoWhatsappService adicionarNotificacaoWhatsappService;


    @Transactional
    public void cadastrar(CadastrarResponsavelRequest request) {

        validarResponsavelService.porEmail(request.getEmail());

        validarResponsavelService.porCelular(request.getCelular());

        for(Long id : request.getIdAlunos()){

            validaAlunoService.porId(id);
        }

        Responsavel responsavel = toEntity(request);
        responsavel.setSenha(passwordEncoder.encode(responsavel.getEmail()));
        responsavel.setAtivo(true);
        responsavel.setAlunos(new ArrayList<>());
        responsavel.setPermissoes(new ArrayList<>());
        responsavel.setNotificacoesEmail(new ArrayList<>());
        responsavel.setNotificacoesWhatsapp(new ArrayList<>());

        adicionarPermissaoResponsavelService.adicionar(responsavel);

        for(Long id : request.getIdAlunos()){

            Aluno aluno = alunoRepository.findById(id).get();

            List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

            aluno.setResponsaveis(responsaveis);

            responsavel.adicionarAluno(aluno);

            adicionarNotificacaoEmailService.adicionar(responsavel, aluno);

            adicionarNotificacaoWhatsappService.adicionar(responsavel, aluno);
        }

        responsavelRepository.save(responsavel);
    }
}
