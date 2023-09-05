package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.response.PesquisarAlunoResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;

public class AlunoMapper {

    public static PesquisarAlunoResponse toPesquisarAlunoResponse(Aluno aluno){

        return PesquisarAlunoResponse
                .builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .build();
    }
}
