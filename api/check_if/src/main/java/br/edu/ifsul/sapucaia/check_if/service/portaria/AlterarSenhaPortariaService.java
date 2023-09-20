package br.edu.ifsul.sapucaia.check_if.service.portaria;

import br.edu.ifsul.sapucaia.check_if.controller.request.AlterarSenhaRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.repository.PortariaRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarNovaSenhaValidator;
import br.edu.ifsul.sapucaia.check_if.validator.ValidarSenhaAtualValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarSenhaPortariaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidarSenhaAtualValidator validarSenhaAtualValidator;

    @Autowired
    private ValidarNovaSenhaValidator validarNovaSenhaValidator;

    @Autowired
    private PortariaRepository portariaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void alterar(AlterarSenhaRequest request) {

        Portaria portaria = usuarioAutenticadoService.getPortaria();

        validarSenhaAtualValidator.validar(request.getSenhaAtual(), portaria.getSenha());

        validarNovaSenhaValidator.validar(request.getSenhaAtual(), request.getNovaSenha());

        portaria.setSenha(passwordEncoder.encode(request.getNovaSenha()));

        portariaRepository.save(portaria);
    }
}
