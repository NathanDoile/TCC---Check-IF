package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;

public class ChegadasAtrasadasMapper {

    public static ChegadaAtrasadaResponse toResponse(ChegadaAtrasada chegadaAtrasada) {

        return ChegadaAtrasadaResponse
                .builder()
                .id(chegadaAtrasada.getId())
                .data(chegadaAtrasada.getDataHora().toLocalDate())
                .hora(chegadaAtrasada.getDataHora().toLocalTime())
                .nome(chegadaAtrasada.getAluno().getNome())
                .matricula(chegadaAtrasada.getAluno().getMatricula())
                .professor(chegadaAtrasada.getProfessor().getNome())
                .disciplina(chegadaAtrasada.getDisciplina())
                .motivo(chegadaAtrasada.getMotivo())
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
