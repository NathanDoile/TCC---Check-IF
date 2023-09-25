package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelEmLoteRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.CadastrarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.parse;
import static java.util.List.of;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class CadastrarResponsavelEmLoteService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private CadastrarResponsavelService cadastrarResponsavelService;

    @Transactional
    public void cadastrar(CadastrarResponsavelEmLoteRequest request) throws Exception {

        Workbook workbook = new Workbook("teste.xlsx");

        WorksheetCollection collection  = workbook.getWorksheets();

        for (int worksheetIndex = 0; worksheetIndex < collection.getCount(); worksheetIndex++) {

            Worksheet planilha = collection.get(worksheetIndex);

            int linhas = planilha.getCells().getMaxDataRow();

            for (int i = 1; i <= linhas; i++) {

                String nomeAluno = planilha.getCells().get(i, 0).getValue().toString();

                String matricula = planilha.getCells().get(i, 1).getValue().toString();

                String turma = planilha.getCells().get(i, 2).getValue().toString();

                LocalDate dataNascimento = parse(planilha.getCells().get(i, 3).getValue().toString()).toLocalDate();

                String nomeResponsavel = planilha.getCells().get(i, 4).getValue().toString();

                Object emailObject = planilha.getCells().get(i, 5).getValue();

                long celular = 0L;

                if(nonNull(planilha.getCells().get(i, 6).getValue())){

                    celular = parseLong(planilha.getCells().get(i, 6).getValue()
                            .toString()
                            .replace(".", "")
                            .substring(0, 11));
                }

                if(isNull(emailObject)){

                    if(!alunoRepository.existsByMatricula(matricula)){

                        Aluno aluno = Aluno
                                .builder()
                                .nome(nomeAluno)
                                .matricula(matricula)
                                .turma(turma)
                                .dataNascimento(dataNascimento)
                                .isAtivo(true)
                                .build();

                        alunoRepository.save(aluno);
                    }
                }
                else if(alunoRepository.existsByMatricula(matricula)){

                    String email = emailObject.toString();

                    Aluno aluno = alunoRepository.findByMatricula(matricula);

                    if(responsavelRepository.existsByEmail(email)){

                        Responsavel responsavel = responsavelRepository.findByEmail(email);

                        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

                        if(!responsaveis.contains(responsavel)){

                            responsaveis.add(responsavel);

                            aluno.setResponsaveis(responsaveis);

                            alunoRepository.save(aluno);
                        }
                    }
                    else{

                        CadastrarResponsavelRequest cadastrarResponsavelRequest = CadastrarResponsavelRequest
                                .builder()
                                .nome(nomeResponsavel)
                                .email(email)
                                .celular(celular)
                                .idAlunos(of(aluno.getId()))
                                .build();

                        cadastrarResponsavelService.cadastrar(cadastrarResponsavelRequest);
                    }

                }
                else{

                    String email = emailObject.toString();

                    Aluno aluno = Aluno
                            .builder()
                            .nome(nomeAluno)
                            .matricula(matricula)
                            .turma(turma)
                            .dataNascimento(dataNascimento)
                            .isAtivo(true)
                            .build();

                    alunoRepository.save(aluno);

                    if(responsavelRepository.existsByEmail(email)){

                        Responsavel responsavel = responsavelRepository.findByEmail(email);

                        aluno.setResponsaveis(new ArrayList<>());
                        aluno.getResponsaveis().add(responsavel);

                        alunoRepository.save(aluno);
                    }
                    else{

                        CadastrarResponsavelRequest cadastrarResponsavelRequest = CadastrarResponsavelRequest
                                .builder()
                                .nome(nomeResponsavel)
                                .email(email)
                                .celular(celular)
                                .idAlunos(of(aluno.getId()))
                                .build();

                        cadastrarResponsavelService.cadastrar(cadastrarResponsavelRequest);
                    }
                }
            }
        }
    }
}
