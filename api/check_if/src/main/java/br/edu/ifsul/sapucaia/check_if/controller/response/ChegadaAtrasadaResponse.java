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
public class ChegadaAtrasadaResponse {

    private Long id;

    private String data;

    private String hora;

    private String nome;

    private String matricula;

    private String professor;

    private String disciplina;

    private String motivo;

    private String turma;
}
