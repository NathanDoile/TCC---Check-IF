package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.*;
import br.edu.ifsul.sapucaia.check_if.service.responsavel.*;
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

    @Autowired
    private CadastrarResponsavelEmLoteService cadastrarResponsavelEmLoteService;

    @Autowired
    private AlterarResponsavelService alterarResponsavelService;

    @Autowired
    private AlterarSenhaResponsavelService alterarSenhaResponsavelService;

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping
    @ResponseStatus(CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarResponsavelRequest request) {
        cadastrarResponsavelService.cadastrar(request);
    }

    @Secured("ROLE_RESPONSAVEL")
    @PutMapping("/alterar-notificacao")
    @ResponseStatus(OK)
    public void alterarNotificacao(@Valid @RequestBody AlterarNotificacaoResponsavelRequest request){
        alterarNotificacaoResponsavelService.alterar(request);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("/lote")
    @ResponseStatus(CREATED)
    public void cadastrarEmLote(@Valid @RequestBody CadastrarResponsavelEmLoteRequest request) throws Exception {
        cadastrarResponsavelEmLoteService.cadastrar(request);
    }

    @Secured("ROLE_RESPONSAVEL")
    @PutMapping
    @ResponseStatus(OK)
    public void alterar(@Valid @RequestBody AlterarResponsavelRequest request){
        alterarResponsavelService.alterar(request);
    }


    @Secured("ROLE_RESPONSAVEL")
    @PutMapping("/senha")
    @ResponseStatus(OK)
    public void alterarSenha(@Valid @RequestBody AlterarSenhaResponsavelRequest request){
        alterarSenhaResponsavelService.alterar(request);
    }
}
