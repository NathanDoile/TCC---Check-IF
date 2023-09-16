package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarAdministradorRequest;
import br.edu.ifsul.sapucaia.check_if.service.administrador.CadastrarAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private CadastrarAdministradorService cadastrarAdministradorService;



    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping
    @ResponseStatus(CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarAdministradorRequest request){
        cadastrarAdministradorService.cadastrar(request);
    }
}
