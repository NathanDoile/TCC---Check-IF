package br.edu.ifsul.sapucaia.check_if.service.administrador;

import br.edu.ifsul.sapucaia.check_if.controller.request.AlterarSenhaAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarNovaSenhaValidator;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarSenhaAtualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarSenhaAdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidarSenhaAtualValidator validarSenhaAtualValidator;

    @Autowired
    private ValidarNovaSenhaValidator validarNovaSenhaValidator;

    @Transactional
    public void alterar(AlterarSenhaAdministradorRequest request) {

        Administrador administrador = usuarioAutenticadoService.getAdministrador();

        validarSenhaAtualValidator.validar(request.getSenhaAtual(), administrador.getSenha());

        validarNovaSenhaValidator.validar(request.getSenhaAtual(), request.getNovaSenha());

        administrador.setSenha(passwordEncoder.encode(request.getNovaSenha()));

        administradorRepository.save(administrador);
    }
}
