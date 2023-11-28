package br.edu.ifsul.sapucaia.check_if.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PesquisarAlunoResponse {

    private Long id;

    private String nome;

    private String matricula;

    private String turma;

    private String dataNascimento;
}
