package br.edu.ifsul.sapucaia.check_if.security.service;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.PortariaRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.UsuarioSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioSecurityService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PortariaRepository portariaRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(administradorRepository.existsByEmail(email)){
            Administrador usuario = administradorRepository.findByEmail(email);

            return new UsuarioSecurity(usuario);
        }
        else if(portariaRepository.existsByEmail(email)){
            Portaria usuario = portariaRepository.findByEmail(email);

            return new UsuarioSecurity(usuario);
        }
        else{
            Responsavel usuario = responsavelRepository.findByEmail(email);

            return new UsuarioSecurity(usuario);
        }
    }
}