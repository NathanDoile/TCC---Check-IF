package br.edu.ifsul.sapucaia.check_if.security.service;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.mapper.AdministradorMapper;
import br.edu.ifsul.sapucaia.check_if.mapper.PortariaMapper;
import br.edu.ifsul.sapucaia.check_if.mapper.ResponsavelMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.PortariaRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.UsuarioSecurity;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;
import br.edu.ifsul.sapucaia.check_if.security.domain.Enum.Funcao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.sapucaia.check_if.security.domain.Enum.Funcao.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PortariaRepository portariaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public UsuarioSecurity getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UsuarioSecurity usuarioSecurity = (UsuarioSecurity) authentication.getPrincipal();

        return usuarioSecurity;
    }

    public Administrador getAdministrador() {

        UsuarioSecurity user = getUser();

        if (isNull(user)) {
            return null;
        }

        return administradorRepository.findById(user.getId()).orElse(null);
    }

    public Responsavel getResponsavel() {

        UsuarioSecurity user = getUser();

        if (isNull(user)) {
            return null;
        }

        return responsavelRepository.findById(user.getId()).orElse(null);
    }

    public Portaria getPortaria() {

        UsuarioSecurity user = getUser();

        if (isNull(user)) {
            return null;
        }

        return portariaRepository.findById(user.getId()).orElse(null);
    }

    public UsuarioResponse getResponse() {

        UsuarioSecurity user = getUser();

        if(user.getAuthorities().get(0).toString().equals(ADMINISTRADOR.getRole())){
            Administrador entity = getAdministrador();
            return nonNull(entity) ? AdministradorMapper.toResponse(entity) : new UsuarioResponse();
        }
        else if(user.getAuthorities().get(0).toString().equals(PORTARIA.getRole())){
            Portaria entity = getPortaria();
            return nonNull(entity) ? PortariaMapper.toResponse(entity) : new UsuarioResponse();
        }
        else if(user.getAuthorities().get(0).toString().equals(RESPONSAVEL.getRole())){
            Responsavel entity = getResponsavel();
            return nonNull(entity) ? ResponsavelMapper.toResponse(entity) : new UsuarioResponse();
        }

        return null;
    }
}
