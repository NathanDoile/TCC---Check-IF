package br.edu.ifsul.sapucaia.check_if.service.administrador;

import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.repository.AdministradorRepository;
import br.edu.ifsul.sapucaia.check_if.repository.PermissaoRepository;
import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import br.edu.ifsul.sapucaia.check_if.service.permissao.CadastrarPermissaoAdministradorService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidarAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.sapucaia.check_if.mapper.AdministradorMapper.toEntity;
import static java.util.List.of;

@Service
public class CadastrarAdministradorService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ValidarAdministradorService validarAdministradorService;

    @Autowired
    private CadastrarPermissaoAdministradorService cadastrarPermissaoAdministradorService;

    public void cadastrar(CadastrarAdministradorRequest request) {

        validarAdministradorService.porEmail(request.getEmail());

        validarAdministradorService.porSiape(request.getSiape());

        Permissao permissao = cadastrarPermissaoAdministradorService.cadastrar();

        Administrador administrador = toEntity(request);
        administrador.setSenha(passwordEncoder.encode(administrador.getSiape()));
        administrador.setAtivo(true);
        administrador.setPermissoes(of(permissao));

        permissao.setAdministrador(administrador);

        administradorRepository.save(administrador);

        permissaoRepository.save(permissao);
    }
}
