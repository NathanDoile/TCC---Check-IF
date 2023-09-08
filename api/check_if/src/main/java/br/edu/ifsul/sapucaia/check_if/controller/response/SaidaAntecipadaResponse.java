package br.edu.ifsul.sapucaia.check_if.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SaidaAntecipadaResponse {

    private Long id;

    private LocalDate dataAutorizada;

    private LocalTime horaAutorizada;

    private String nome;

    private String turma;
}
