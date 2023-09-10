package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;

public class ChegadasAtrasadasMapper {

    public static ChegadaAtrasadaResponse toResponse(ChegadaAtrasada chegadaAtrasada, Aluno aluno) {

        return ChegadaAtrasadaResponse
                .builder()
                .id(chegadaAtrasada.getId())
                .dataHora(chegadaAtrasada.getDataHora())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .build();
    }

    public static ChegadaAtrasada toEntity(RegistrarChegadaAtrasadaRequest request) {

        return ChegadaAtrasada
                .builder()
                .motivo(request.getMotivo())
                .disciplina(request.getDisciplina())
                .build();
    }
}
