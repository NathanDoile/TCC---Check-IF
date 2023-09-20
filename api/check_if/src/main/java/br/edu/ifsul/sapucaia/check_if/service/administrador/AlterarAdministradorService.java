package br.edu.ifsul.sapucaia.check_if.service.administrador;

import br.edu.ifsul.sapucaia.check_if.controller.request.administrador.AlterarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@Service
public class AlterarAdministradorService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional
    public void alterar(AlterarAdministradorRequest request) {

        Administrador administrador = usuarioAutenticadoService.getAdministrador();

        if(administradorRepository.existsByEmailAndIdNot(request.getEmail(), administrador.getId())){
            throw new ResponseStatusException(CONFLICT, "E-mail já vinculado a outro usuário.");
        }

        administrador.setNome(request.getNome());
        administrador.setEmail(request.getEmail());

        administradorRepository.save(administrador);
    }
}
