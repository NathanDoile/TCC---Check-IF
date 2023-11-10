package br.edu.ifsul.sapucaia.check_if.security.service.validator;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidarTokenService {

    public static final String MENSAGEM_PADRAO_TOKEN_INVALIDO = "Token inválido.";

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public void validarAdministrador(Administrador administrador, String token) {

        if(!administrador.getTokenResetarSenha().equals(token)) {

            administrador.setTentativasResetarSenha(administrador.getTentativasResetarSenha() + 1);

            administradorRepository.save(administrador);

            throw new ResponseStatusException(BAD_REQUEST, MENSAGEM_PADRAO_TOKEN_INVALIDO);
        }
    }

    public void validarResponsavel(Responsavel responsavel, String token) {

        if(!responsavel.getTokenResetarSenha().equals(token)) {

            responsavel.setTentativasResetarSenha(responsavel.getTentativasResetarSenha() + 1);
            System.out.println(responsavel.getTentativasResetarSenha());
            responsavelRepository.save(responsavel);

            throw new ResponseStatusException(BAD_REQUEST, MENSAGEM_PADRAO_TOKEN_INVALIDO);
        }
    }
}
