package br.edu.ifsul.sapucaia.check_if.controller;

import br.edu.ifsul.sapucaia.check_if.controller.request.aluno.GerarRelatorioAlunoRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.aluno.LerCrachaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.AlunoResponse;
import br.edu.ifsul.sapucaia.check_if.controller.response.CrachaResponse;
import br.edu.ifsul.sapucaia.check_if.controller.response.PesquisarAlunoResponse;
import br.edu.ifsul.sapucaia.check_if.service.aluno.GerarRelatorioAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.aluno.LerCrachaService;
import br.edu.ifsul.sapucaia.check_if.service.aluno.ObterAlunoService;
import br.edu.ifsul.sapucaia.check_if.service.aluno.PesquisarAlunoService;
import com.aspose.pdf.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;

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

    @Autowired
    private LerCrachaService lerCrachaService;

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/pesquisar")
    @ResponseStatus(OK)
    public Page<PesquisarAlunoResponse> pesquisar(@RequestParam String texto, Pageable pageable){
        return pesquisarAlunoService.pesquisar(texto, pageable);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @GetMapping("/{id}/obter")
    @ResponseStatus(OK)
    public AlunoResponse obter(@PathVariable Long id){
        return obterAlunoService.obter(id);
    }

    @Secured("ROLE_ADMINISTRADOR")
    @PostMapping("/gerar-relatorio")
    @ResponseStatus(OK)
    public byte[] gerarRelatorio(@Valid @RequestBody GerarRelatorioAlunoRequest request) throws IOException {
        return gerarRelatorioAlunoService.gerar(request);
    }

    @PostMapping("/ler-cracha/publico")
    @ResponseStatus(OK)
    public CrachaResponse lerCracha(@Valid @RequestBody LerCrachaRequest request) throws IOException {
        return lerCrachaService.ler(request);
    }
}
