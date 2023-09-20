package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.request.aluno.GerarRelatorioAlunoRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ChegadaAtrasadaRepository;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada.GerarRelatorioChegadaAtrasadaService;
import br.edu.ifsul.sapucaia.check_if.service.saidaantecipada.GerarRelatorioSaidaAntecipadaService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidaMesAnoValidator;
import com.aspose.pdf.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class GerarRelatorioAlunoService {

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private ValidaMesAnoValidator validaMesAnoValidator;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ChegadaAtrasadaRepository chegadaAtrasadaRepository;

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    @Autowired
    private GerarRelatorioSaidaAntecipadaService gerarRelatorioSaidaAntecipadaService;

    @Autowired
    private GerarRelatorioChegadaAtrasadaService gerarRelatorioChegadaAtrasadaService;

    public void gerar(GerarRelatorioAlunoRequest request) {

        validaAlunoService.porId(request.getId());
        validaMesAnoValidator.validarAno(request.getAno());

        LocalDateTime inicioPeriodo, fimPeriodo;

        Aluno aluno = alunoRepository.findById(request.getId()).get();

        if(request.getMes() != 0){
            validaMesAnoValidator.validarMes(request.getMes());

            inicioPeriodo = LocalDateTime.of(
                    LocalDate.of(request.getAno(), request.getMes(), 1),
                    LocalTime.MIN);

            fimPeriodo = LocalDateTime.of(
                    now().withYear(request.getAno()).withMonth(request.getMes()).with(lastDayOfMonth()),
                    LocalTime.MAX);
        }
        else{

            inicioPeriodo = LocalDateTime.of(
                    LocalDate.of(request.getAno(), 1, 1),
                    LocalTime.MIN);

            fimPeriodo = LocalDateTime.of(
                    now().withYear(request.getAno()).withMonth(request.getMes()).with(lastDayOfMonth()),
                    LocalTime.MAX);
        }

        Document doc = new Document();

        List<SaidaAntecipada> saidasAntecipadas = saidaAntecipadaRepository.
                findAllByDataHoraSaidaBetweenAndAlunoOrderByDataHoraSaida(inicioPeriodo, fimPeriodo, aluno);

        gerarRelatorioSaidaAntecipadaService.gerar(doc, saidasAntecipadas, aluno);

        List<ChegadaAtrasada> chegadasAtrasadas = chegadaAtrasadaRepository.
                findAllByDataHoraBetweenAndAlunoOrderByDataHora(inicioPeriodo, fimPeriodo, aluno);

        gerarRelatorioChegadaAtrasadaService.gerar(doc, chegadasAtrasadas, aluno);

        doc.save("Relatório " + aluno.getNome() + ".pdf");
    }
}
