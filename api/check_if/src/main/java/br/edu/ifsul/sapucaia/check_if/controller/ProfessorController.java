package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.professor.AlterarNotificacaoProfessorRequest;
import br.edu.ifsul.sapucaia.check_if.service.professor.AlterarNotificacaoProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private AlterarNotificacaoProfessorService alterarNotificacaoProfessorService;

    @Secured("ROLE_ADMINISTRADOR")
    @PutMapping("/alterar-notificacao")
    @ResponseStatus(OK)
    public void alterarNotificacao(@Valid @RequestBody AlterarNotificacaoProfessorRequest request){
        alterarNotificacaoProfessorService.alterar(request);
    }
}
