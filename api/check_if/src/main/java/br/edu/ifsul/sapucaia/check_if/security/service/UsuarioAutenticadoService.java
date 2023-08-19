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
import br.edu.ifsul.sapucaia.check_if.security.config.UsuarioSecurity;
import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

        Long id = getUser().getId();

        if (isNull(id)) {
            return null;
        }

        return administradorRepository.findById(id).orElse(null);
    }

    public Responsavel getResponsavel() {
        Long id = getUser().getId();

        if (isNull(id)) {
            return null;
        }

        return responsavelRepository.findById(id).orElse(null);
    }

    public Portaria getPortaria() {
        Long id = getUser().getId();

        if (isNull(id)) {
            return null;
        }

        return portariaRepository.findById(id).orElse(null);
    }

    public UsuarioResponse getResponse() {

        UsuarioSecurity user = getUser();

        if(user.getAuthorities().get(0).equals("ROLE_ADMINISTRADOR")){
            Administrador entity = getAdministrador();
            return nonNull(entity) ? AdministradorMapper.toResponse(entity) : new UsuarioResponse();
        }
        else if(user.getAuthorities().get(0).equals("ROLE_PORTARIA")){
            Portaria entity = getPortaria();
            return nonNull(entity) ? PortariaMapper.toResponse(entity) : new UsuarioResponse();
        }
        else if(user.getAuthorities().get(0).equals("ROLE_RESPONSAVEL")){
            Responsavel entity = getResponsavel();
            return nonNull(entity) ? ResponsavelMapper.toResponse(entity) : new UsuarioResponse();
        }

        return null;
    }
}
