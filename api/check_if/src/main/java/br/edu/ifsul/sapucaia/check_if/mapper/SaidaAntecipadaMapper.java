package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.CadastrarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.SolicitarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public class SaidaAntecipadaMapper {


    public static SaidaAntecipadaResponse toResponse(SaidaAntecipada saidaAntecipada) {

        if(saidaAntecipada.getDataHoraSaida() == null){
            return SaidaAntecipadaResponse
                    .builder()
                    .id(saidaAntecipada.getId())
                    .dataAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalDate())
                    .horaAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalTime())
                    .nome(saidaAntecipada.getAluno().getNome())
                    .turma(saidaAntecipada.getAluno().getTurma())
                    .matricula(saidaAntecipada.getAluno().getMatricula())
                    .build();
        }

        return SaidaAntecipadaResponse
                .builder()
                .id(saidaAntecipada.getId())
                .dataAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalDate())
                .horaAutorizada(saidaAntecipada.getDataHoraAutorizada().toLocalTime())
                .nome(saidaAntecipada.getAluno().getNome())
                .turma(saidaAntecipada.getAluno().getTurma())
                .matricula(saidaAntecipada.getAluno().getMatricula())
                .dataSaida(saidaAntecipada.getDataHoraSaida().toLocalDate().format(ofPattern("dd/MM/yyyy")))
                .horaSaida(saidaAntecipada.getDataHoraSaida().toLocalTime())
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
