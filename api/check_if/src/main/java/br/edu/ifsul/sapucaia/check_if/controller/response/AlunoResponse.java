package br.edu.ifsul.sapucaia.check_if.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlunoResponse {

    private Long id;

    private String nome;

    private String matricula;

    private String turma;

    private LocalDate dataNascimento;

    private List<String> responsaveis;
}
