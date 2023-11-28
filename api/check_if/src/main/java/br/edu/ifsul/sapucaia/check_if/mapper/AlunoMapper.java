package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.response.AlunoResponse;
import br.edu.ifsul.sapucaia.check_if.controller.response.PesquisarAlunoResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;

import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class AlunoMapper {

    public static PesquisarAlunoResponse toPesquisarAlunoResponse(Aluno aluno){

        return PesquisarAlunoResponse
                .builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .turma(aluno.getTurma())
                .dataNascimento(aluno.getDataNascimento().format(ofPattern("dd/MM/yyyy")))
                .build();
    }

    public static AlunoResponse toResponse(Aluno aluno) {

        return AlunoResponse
                .builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .turma(aluno.getTurma())
                .dataNascimento(aluno.getDataNascimento())
                .build();
    }

    public static AlunoResponse toResponseWithResponsavel(Aluno aluno, List<Responsavel> responsaveis){

        return AlunoResponse
                .builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .turma(aluno.getTurma())
                .dataNascimento(aluno.getDataNascimento())
                .responsaveis(responsaveis.stream().map(Responsavel::getNome).toList())
                .build();
    }
}
