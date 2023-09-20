package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada.ObterChegadasAtrasadasService;
import br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada.RegistrarChegadaAtrasadaManualService;
import br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada.RegistrarChegadaAtrasadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/chegadas-atrasadas")
public class ChegadaAtrasadaController {

    @Autowired
    private ObterChegadasAtrasadasService obterChegadasAtrasadasService;

    @Autowired
    private RegistrarChegadaAtrasadaManualService registrarChegadaAtrasadaManualService;

    @Autowired
    private RegistrarChegadaAtrasadaService registrarChegadaAtrasadaService;

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/{data}/obter")
    @ResponseStatus(OK)
    public Page<ChegadaAtrasadaResponse> obterChegadasAtrasadas(@PathVariable String data, Pageable pageable){
        return obterChegadasAtrasadasService.obter(data, pageable);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("/registrar/manual")
    @ResponseStatus(CREATED)
    public ChegadaAtrasadaResponse registrarManual(@Valid @RequestBody RegistrarChegadaAtrasadaRequest request){
        return registrarChegadaAtrasadaManualService.registrar(request);
    }

    @PostMapping("/registrar/cracha/publico")
    @ResponseStatus(CREATED)
    public ChegadaAtrasadaResponse registrar(@Valid @RequestBody RegistrarChegadaAtrasadaRequest request){
        return registrarChegadaAtrasadaService.registrar(request);
    }
}
