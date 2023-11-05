package br.edu.ifsul.sapucaia.check_if.security.service.validator;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
public class ValidaValidadeTokenService {

    public static final int TEMPO_EXPIRACAO_CODIGO_SEGUNDOS = 300;

    public static final String MENSAGEM_PADRAO_TOKEN_EXPIRADO = "Token expirado, por favor, gere um novo " +
            "token para alterar sua senha.";

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public void validarAdministrador(Administrador administrador) {

        long diferenca = Duration.between(administrador.getDataEnvioToken(), now()).getSeconds();

        if(diferenca > TEMPO_EXPIRACAO_CODIGO_SEGUNDOS){

            administrador.setTokenResetarSenha(null);

            administradorRepository.save(administrador);

            throw new ResponseStatusException(NOT_ACCEPTABLE,MENSAGEM_PADRAO_TOKEN_EXPIRADO);
        }
    }

    public void validarResponsavel(Responsavel responsavel) {

        long diferenca = Duration.between(responsavel.getDataEnvioToken(), now()).getSeconds();

        if(diferenca > TEMPO_EXPIRACAO_CODIGO_SEGUNDOS){

            responsavel.setTokenResetarSenha(null);

            responsavelRepository.save(responsavel);

            throw new ResponseStatusException(NOT_ACCEPTABLE,MENSAGEM_PADRAO_TOKEN_EXPIRADO);
        }
    }
}
