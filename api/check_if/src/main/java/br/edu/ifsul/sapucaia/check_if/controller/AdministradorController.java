package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.AlterarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.AlterarSenhaAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.service.administrador.AlterarAdministradorService;
import br.edu.ifsul.sapucaia.check_if.service.administrador.AlterarSenhaAdministradorService;
import br.edu.ifsul.sapucaia.check_if.service.administrador.CadastrarAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private CadastrarAdministradorService cadastrarAdministradorService;

    @Autowired
    private AlterarAdministradorService alterarAdministradorService;

    @Autowired
    private AlterarSenhaAdministradorService alterarSenhaAdministradorService;

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping
    @ResponseStatus(CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarAdministradorRequest request){
        cadastrarAdministradorService.cadastrar(request);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PutMapping
    @ResponseStatus(OK)
    public void alterar(@Valid @RequestBody AlterarAdministradorRequest request){
        alterarAdministradorService.alterar(request);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PutMapping("/alterar-senha")
    @ResponseStatus(OK)
    public void alterarSenha(@Valid @RequestBody AlterarSenhaAdministradorRequest request){
        alterarSenhaAdministradorService.alterar(request);
    }
}
