package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada.ObterChegadasAtrasadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/chegadas-atrasadas")
public class ChegadaAtrasadaController {

    @Autowired
    private ObterChegadasAtrasadasService obterChegadasAtrasadasService;

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/{data}/obter")
    @ResponseStatus(OK)
    public Page<ChegadaAtrasadaResponse> obterChegadasAtrasadas(@PathVariable String data, Pageable pageable){
        return obterChegadasAtrasadasService.obter(data, pageable);
    }
}
