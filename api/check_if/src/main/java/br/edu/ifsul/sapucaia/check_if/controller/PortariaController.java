package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.AlterarSenhaRequest;
import br.edu.ifsul.sapucaia.check_if.service.portaria.AlterarSenhaPortariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/portarias")
public class PortariaController {

    @Autowired
    private AlterarSenhaPortariaService alterarSenhaPortariaService;

    @Secured("ROLE_PORTARIA")
    @PutMapping("/alterar-senha")
    @ResponseStatus(OK)
    public void alterar(@Valid @RequestBody AlterarSenhaRequest request){
        alterarSenhaPortariaService.alterar(request);
    }
}
