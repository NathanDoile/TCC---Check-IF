package br.edu.ifsul.sapucaia.check_if.service.administrador;

import br.edu.ifsul.sapucaia.check_if.controller.request.administrador.CadastrarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.PermissaoRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.service.permissao.CadastrarPermissaoAdministradorService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.mapper.AdministradorMapper.toEntity;
import static java.util.List.of;

@Service
public class CadastrarAdministradorService {

    public static final String MENSAGEM_PADRAO_NOVO_RESPONSAVEL =
            "Seja muito bem-vindo ao Check-IF, do campus Sapucaia do Sul! \n\n" +
                    "Você é um novo administrador da nossa plataforma, todas as funcionalidades serão explicadas pelo servidor " +
                    "que lhe cadastrou no sistema, o seu login foi gerado automaticamente com os seguintes" +
                    " dados: \n\n" +
                    "E-MAIL: Esse mesmo que você recebeu essa mensagem \n" +
                    "SENHA: Sua matrícula do SIAPE \n\n" +
                    "Será exigido que você altere sua senha no primeiro acesso e, para a sua segurança e dos outros usuários" +
                    ", não informe ela para ninguém. \n\n" +
                    "Em caso de dúvidas ou problemas, contate outro servidor do Apoio Acadêmico ou a equipe do Check-IF.\n\n" +
                    "Atenciosamente, \n" +
                    "Check-IF";

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ValidaAdministradorService validaAdministradorService;

    @Autowired
    private CadastrarPermissaoAdministradorService cadastrarPermissaoAdministradorService;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Transactional
    public void cadastrar(CadastrarAdministradorRequest request) {

        validaAdministradorService.porEmail(request.getEmail());

        validaAdministradorService.porSiape(request.getSiape());

        Permissao permissao = cadastrarPermissaoAdministradorService.cadastrar();

        Administrador administrador = toEntity(request);
        administrador.setSenha(passwordEncoder.encode(administrador.getSiape()));
        administrador.setAtivo(true);
        administrador.setPermissoes(of(permissao));

        permissao.setAdministrador(administrador);

        administradorRepository.save(administrador);

        permissaoRepository.save(permissao);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Seja bem-vindo ao Check-IF, " + administrador.getNome())
                .mensagem(MENSAGEM_PADRAO_NOVO_RESPONSAVEL)
                .build();

        enviarEmailService.enviarCriarAdministrador(enviarEmailRequest, administrador);
    }
}
