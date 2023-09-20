package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarNotificacaoResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.service.responsavel.AlterarNotificacaoResponsavelService;
import br.edu.ifsul.sapucaia.check_if.service.responsavel.CadastrarResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    @Autowired
    private CadastrarResponsavelService cadastrarResponsavelService;

    @Autowired
    private AlterarNotificacaoResponsavelService alterarNotificacaoResponsavelService;

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping
    @ResponseStatus(CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarResponsavelRequest request) {
        cadastrarResponsavelService.cadastrar(request);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PutMapping("/alterar-notificacao")
    @ResponseStatus(OK)
    public void alterarNotificacao(@Valid @RequestBody AlterarNotificacaoResponsavelRequest request){
        alterarNotificacaoResponsavelService.alterar(request);
    }
}
