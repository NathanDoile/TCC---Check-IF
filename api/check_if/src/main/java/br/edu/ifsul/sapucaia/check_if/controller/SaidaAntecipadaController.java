package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.SolicitarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.service.saidaantecipada.CadastrarSaidaAntecipadaService;
import br.edu.ifsul.sapucaia.check_if.service.saidaantecipada.ObterSaidasAntecipadasService;
import br.edu.ifsul.sapucaia.check_if.service.saidaantecipada.SolicitarSaidaAntecipadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping("/saidas-antecipadas")
public class SaidaAntecipadaController {

    @Autowired
    private ObterSaidasAntecipadasService obterSaidasAntecipadasService;

    @Autowired
    private SolicitarSaidaAntecipadaService solicitarSaidaAntecipadaService;

    @Autowired
    private CadastrarSaidaAntecipadaService cadastrarSaidaAntecipadaService;

    @Secured("ROLE_PORTARIA")
    @GetMapping
    @ResponseStatus(FOUND)
    public Page<SaidaAntecipadaResponse> obterSaidasAntecipadas(Pageable pageable){
        return obterSaidasAntecipadasService.obter(pageable);
    }

    @Secured("ROLE_RESPONSAVEL")
    @PostMapping("/solicitar")
    @ResponseStatus(CREATED)
    public SaidaAntecipadaResponse solicitar(@Valid @RequestBody SolicitarSaidaAntecipadaRequest request){
        return solicitarSaidaAntecipadaService.solicitar(request);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("/cadastrar")
    @ResponseStatus(CREATED)
    public SaidaAntecipadaResponse cadastrar(@Valid @RequestBody CadastrarSaidaAntecipadaRequest request){
        return cadastrarSaidaAntecipadaService.cadastrar(request);
    }
}
