package br.edu.ifsul.sapucaia.check_if.security.service;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.AtualizarMinhaSenhaRequest;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.GerarTokenRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.validator.ValidaValidadeTokenService;
import br.edu.ifsul.sapucaia.check_if.security.service.validator.ValidarTentativasService;
import br.edu.ifsul.sapucaia.check_if.security.service.validator.ValidarTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDateTime.now;
import static net.bytebuddy.utility.RandomString.make;

@Service
public class AtualizarRedefinirSenhaService {

    public static final String MENSAGEM_PADRAO_ESQUECEU_SUA_SENHA_PARA_EMAIL = "Alterar senha - Check-IF";

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private ValidaValidadeTokenService validaValidadeTokenService;

    @Autowired
    private ValidarTentativasService validarTentativasService;

    @Autowired
    private ValidarTokenService validarTokenService;

    @Transactional
    public void esqueciSenha(GerarTokenRequest request) {

        String email = request.getEmail();

        String token = make(50);

        String conteudo =
                "Prezado(a),\n" +
                        "Encaminhamos seu token para redefinição da sua senha. Para sua segurança, não o " +
                        "compartilhe com ninguém.\n\n" +
                        "Token: " + token + "\n\n" +
                        "Observações:\n" +
                        "- Seu token tem validade de 5 minutos desde sua requisição, após esse tempo, " +
                        "solicite outro token.\n" +
                        "- Você possui 3 tentativas para acertar seu token, se errar as 3 chances, seu " +
                        "token é invalidado e deve-se solicitar outro.\n\n" +
                        "Atenciosamente,\n" +
                        "Check-IF.";

        atualizarTokenResetarSenha(token, email);

        enviarEmailService.enviarPorEsqueceuSuaSenha(email,
                MENSAGEM_PADRAO_ESQUECEU_SUA_SENHA_PARA_EMAIL, conteudo);
    }

    @Transactional
    public void atualizaSenha(AtualizarMinhaSenhaRequest request) {

        String token = request.getToken();
        String novaSenha = request.getSenha();
        String email = request.getEmail();

        if(administradorRepository.existsByEmail(email)){

            Administrador administrador = administradorRepository.findByEmail(email);

            validarTentativasService.validarAdministrador(administrador);

            validarTokenService.validarAdministrador(administrador, token);

            validaValidadeTokenService.validarAdministrador(administrador);

            administrador.setSenha(passwordEncoder.encode(novaSenha));
            administrador.setTokenResetarSenha(null);

            administradorRepository.save(administrador);
        }else{

            Responsavel responsavel = responsavelRepository.findByEmail(email);

            validarTentativasService.validarResponsavel(responsavel);

            validarTokenService.validarResponsavel(responsavel, token);

            validaValidadeTokenService.validarResponsavel(responsavel);

            responsavel.setSenha(passwordEncoder.encode(novaSenha));
            responsavel.setTokenResetarSenha(null);

            responsavelRepository.save(responsavel);
        }
    }

    @Transactional
    public void atualizarTokenResetarSenha(String token, String email) {

        if(administradorRepository.existsByEmail(email)){

            Administrador administrador = administradorRepository.findByEmail(email);

            administrador.setTokenResetarSenha(token);
            administrador.setDataEnvioToken(now());
            administrador.setTentativasResetarSenha(0);

            administradorRepository.save(administrador);

        }else{

            Responsavel responsavel = responsavelRepository.findByEmail(email);

            responsavel.setTokenResetarSenha(token);
            responsavel.setDataEnvioToken(now());
            responsavel.setTentativasResetarSenha(0);

            responsavelRepository.save(responsavel);
        }
    }

}