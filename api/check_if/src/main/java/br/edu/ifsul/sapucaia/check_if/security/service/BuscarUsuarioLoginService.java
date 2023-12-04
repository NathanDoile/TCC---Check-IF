package br.edu.ifsul.sapucaia.check_if.security.service;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioLoginService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse buscar() {

        UsuarioResponse usuarioResponse = usuarioAutenticadoService.getResponse();
        usuarioResponse.setPrimeiroAcesso(false);

        if(usuarioResponse.getPermissao().equalsIgnoreCase("ADMINISTRADOR")){

            Administrador administrador = usuarioAutenticadoService.getAdministrador();

            if(passwordEncoder.matches(administrador.getSiape(), administrador.getSenha())){

                usuarioResponse.setPrimeiroAcesso(true);
            }
        }
        else if(usuarioResponse.getPermissao().equalsIgnoreCase("RESPONSAVEL")){

            Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

            if (passwordEncoder.matches(responsavel.getEmail(), responsavel.getSenha())) {

                usuarioResponse.setPrimeiroAcesso(true);
            }
        }

        return usuarioResponse;
    }
}
