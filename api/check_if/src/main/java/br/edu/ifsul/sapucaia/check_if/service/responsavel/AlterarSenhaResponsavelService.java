package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarSenhaResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarNovaSenhaValidator;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarSenhaAtualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AlterarSenhaResponsavelService {

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private ValidarSenhaAtualValidator validarSenhaAtualValidator;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidarNovaSenhaValidator validarNovaSenhaValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void alterar(AlterarSenhaResponsavelRequest request) {

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        validarSenhaAtualValidator.validar(request.getSenhaAntiga(), responsavel.getSenha());

        validarNovaSenhaValidator.validar(request.getSenhaAntiga(), request.getSenhaNova());

        responsavel.setSenha(passwordEncoder.encode(request.getSenhaNova()));

        responsavelRepository.save(responsavel);

    }
}
