package br.edu.ifsul.sapucaia.check_if.service;

import br.edu.ifsul.sapucaia.check_if.controller.request.UsuarioRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.mapper.AdministradorMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.sapucaia.check_if.mapper.AdministradorMapper.toResponse;

@Service
public class IncluirUsuarioService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse incluir(UsuarioRequest request) {

        Administrador usuario = AdministradorMapper.toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setAtivo(true);

        administradorRepository.save(usuario);

        return toResponse(usuario);
    }
}