package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;

import static java.time.format.DateTimeFormatter.ofPattern;

public class ChegadasAtrasadasMapper {

    public static ChegadaAtrasadaResponse toResponse(ChegadaAtrasada chegadaAtrasada) {

        return ChegadaAtrasadaResponse
                .builder()
                .id(chegadaAtrasada.getId())
                .data(chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")))
                .hora(chegadaAtrasada.getDataHora().toLocalTime().toString())
                .nome(chegadaAtrasada.getAluno().getNome())
                .matricula(chegadaAtrasada.getAluno().getMatricula())
                .professor(chegadaAtrasada.getProfessor().getNome())
                .disciplina(chegadaAtrasada.getDisciplina())
                .motivo(chegadaAtrasada.getMotivo())
                .turma(chegadaAtrasada.getAluno().getTurma())
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
