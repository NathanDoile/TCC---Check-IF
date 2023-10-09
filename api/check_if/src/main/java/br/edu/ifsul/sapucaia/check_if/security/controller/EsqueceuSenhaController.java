package br.edu.ifsul.sapucaia.check_if.security.controller;

import br.edu.ifsul.sapucaia.check_if.security.controller.request.AtualizarMinhaSenhaRequest;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.GerarTokenRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.AtualizarRedefinirSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/esqueceu-senha")
public class EsqueceuSenhaController {

    @Autowired
    private AtualizarRedefinirSenhaService atualizarRedefinirSenhaService;

    @PostMapping
    @ResponseStatus(OK)
    public void esqueciSenha(@Valid @RequestBody GerarTokenRequest request) {
        atualizarRedefinirSenhaService.esqueciSenha(request);
    }

    @PostMapping("/atualizar")
    @ResponseStatus(OK)
    public void atualizaSenha(@Valid @RequestBody AtualizarMinhaSenhaRequest request) {
        atualizarRedefinirSenhaService.atualizaSenha(request);
    }
}
