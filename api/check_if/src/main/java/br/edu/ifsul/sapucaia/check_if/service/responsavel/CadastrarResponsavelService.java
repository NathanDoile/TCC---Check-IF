package br.edu.ifsul.sapucaia.check_if.service.responsavel;


import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.service.notificacaoemail.AdicionarNotificacaoEmailService;
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

    public static final String MENSAGEM_PADRAO_NOVO_RESPONSAVEL =
            "Seja muito bem-vindo(a) ao Check-IF, do campus Sapucaia do Sul! \n\n" +
                    "No nosso sistema você poderá solicitar saídas antecipadas de alunos que você tenha vínculo e " +
                    "ver essas solicitações. Ele também enviará notificações para o seu e-mail quando " +
                    "os alunos de seu vínculo saiam antecipadamente ou cheguem atrasados em alguma aula. Você poderá " +
                    "desativar as notificações a qualquer momento na plataforma.\n\n" +
                    "O seu login do primeiro acesso foi criado diretamente pelo nosso sistema com os seguintes dados:\n\n" +
                    "E-MAIL: Esse mesmo que você recebeu essa mensagem\n" +
                    "SENHA: O seu e-mail de login \n\n" +
                    "Será exigido que você altere sua senha no primeiro acesso e, para a sua segurança, não informe " +
                    "ela para ninguém. \n\n" +
                    "Em caso de dúvidas ou problemas, contate o setor do Apoio Acadêmico do IFsul.\n\n" +
                    "Atenciosamente, \n" +
                    "Check-IF";

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
    private EnviarEmailService enviarEmailService;


    @Transactional
    public void cadastrar(CadastrarResponsavelRequest request) {

        validarResponsavelService.porEmail(request.getEmail());
        System.out.println(request.getCelular());
        if(request.getCelular() != null && request.getCelular() != 0){
            validarResponsavelService.porCelular(request.getCelular());
        }

        validaAlunoService.porMatricula(request.getMatricula());

        Responsavel responsavel = toEntity(request);
        responsavel.setSenha(passwordEncoder.encode(responsavel.getEmail()));
        responsavel.setAtivo(true);
        responsavel.setAlunos(new ArrayList<>());
        responsavel.setPermissoes(new ArrayList<>());
        responsavel.setNotificacoesEmail(new ArrayList<>());

        adicionarPermissaoResponsavelService.adicionar(responsavel);

        Aluno aluno = alunoRepository.findByMatriculaAndIsAtivo(request.getMatricula(), true);

        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunosAndIsAtivo(aluno, true);

        aluno.setResponsaveis(responsaveis);

        responsavel.adicionarAluno(aluno);

        adicionarNotificacaoEmailService.adicionar(responsavel, aluno);

        responsavelRepository.save(responsavel);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Seja bem-vindo ao Check-IF, " + responsavel.getNome())
                .mensagem(MENSAGEM_PADRAO_NOVO_RESPONSAVEL)
                .build();

        enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
    }
}
