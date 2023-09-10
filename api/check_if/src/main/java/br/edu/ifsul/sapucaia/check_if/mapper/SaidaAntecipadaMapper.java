package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.CadastrarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.SolicitarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;

import java.time.LocalDateTime;

public class SaidaAntecipadaMapper {


    public static SaidaAntecipadaResponse toResponse(SaidaAntecipada saidaAntecipada, Aluno aluno) {

        return SaidaAntecipadaResponse
                .builder()
                .id(saidaAntecipada.getId())
                .dataAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalDate())
                .horaAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalTime())
                .nome(aluno.getNome())
                .turma(aluno.getTurma())
                .build();
    }

    public static SaidaAntecipada toEntity(SolicitarSaidaAntecipadaRequest request) {

        return SaidaAntecipada
                .builder()
                .dataHoraAutorizada(LocalDateTime.of(request.getDataAutorizada(),request.getHoraAutorizada()))
                .motivo(request.getMotivo())
                .build();
    }

    public static SaidaAntecipada toEntityFromCadastrarSaidaAntecipada(CadastrarSaidaAntecipadaRequest request){

        return SaidaAntecipada
                .builder()
                .nomeResponsavel(request.getNomeResponsavel())
                .grauParentesco(request.getGrauParentesco())
                .motivo(request.getMotivo())
                .build();
    }
}
