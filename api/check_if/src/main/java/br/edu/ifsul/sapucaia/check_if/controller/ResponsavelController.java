package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.service.responsavel.CadastrarResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private CadastrarResponsavelService cadastrarResponsavelService;

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping
    @ResponseStatus(CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarResponsavelRequest request) {
        cadastrarResponsavelService.cadastrar(request);
    }
}
