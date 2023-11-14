package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.response.ProfessorResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;

public class ProfessorMapper {

    public static ProfessorResponse toResponse(Professor professor){

        return ProfessorResponse
                .builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .siape(professor.getSiape())
                .email(professor.getEmail())
                .build();
    }
}
