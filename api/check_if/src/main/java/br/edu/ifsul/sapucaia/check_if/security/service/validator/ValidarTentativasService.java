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
public class ValidarTentativasService {

    public static final String MENSAGEM_PADRAO_ACABARAM_TENTATIVAS = "Suas tentativas acabaram, solicite " +
            "outro token para alterar sua senha.";

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Transactional
    public void validarAdministrador(Administrador administrador) {

        if(administrador.getTentativasResetarSenha() >= 3){

            administrador.setTokenResetarSenha(null);

            administradorRepository.save(administrador);

            throw new ResponseStatusException(BAD_REQUEST, MENSAGEM_PADRAO_ACABARAM_TENTATIVAS);
        }
    }

    @Transactional
    public void validarResponsavel(Responsavel responsavel) {

        if(responsavel.getTentativasResetarSenha() >= 3){

            responsavel.setTokenResetarSenha(null);

            responsavelRepository.save(responsavel);

            throw new ResponseStatusException(BAD_REQUEST, MENSAGEM_PADRAO_ACABARAM_TENTATIVAS);
        }
    }
}