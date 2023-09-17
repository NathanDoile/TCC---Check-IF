package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.GerarRelatorioAlunoRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.AlunoResponse;
import br.edu.ifsul.sapucaia.check_if.controller.response.PesquisarAlunoResponse;
import br.edu.ifsul.sapucaia.check_if.service.aluno.GerarRelatorioAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.aluno.ObterAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.aluno.PesquisarAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private PesquisarAlunoService pesquisarAlunoService;

    @Autowired
    private ObterAlunoService obterAlunoService;

    @Autowired
    private GerarRelatorioAlunoService gerarRelatorioAlunoService;

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/pesquisar")
    @ResponseStatus(FOUND)
    public Page<PesquisarAlunoResponse> pesquisar(@RequestParam String texto, Pageable pageable){
        return pesquisarAlunoService.pesquisar(texto, pageable);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/{id}/obter")
    @ResponseStatus(FOUND)
    public AlunoResponse obter(@PathVariable Long id){
        return obterAlunoService.obter(id);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("/gerar-relatorio")
    @ResponseStatus(OK)
    public void gerarRelatorio(@Valid @RequestBody GerarRelatorioAlunoRequest request){
        gerarRelatorioAlunoService.gerar(request);
    }
}
