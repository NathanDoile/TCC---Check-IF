package br.edu.ifsul.sapucaia.check_if.mapper;

import br.edu.ifsul.sapucaia.check_if.controller.request.professor.CadastrarProfessorRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ProfessorResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.service.professor.CadastrarProfessorService;

public class ProfessorMapper {

    public static ProfessorResponse toResponse(Professor professor){

        return ProfessorResponse
                .builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .siape(professor.getSiape())
                .email(professor.getEmail())
                .celular(professor.getCelular())
                .notificacaoEmail(professor.isNotificacaoEmail())
                .notificacaoWhatsapp(professor.isNotificacaoWhatsapp())
                .build();
    }

    public static Professor toEntity(CadastrarProfessorRequest request){

        return Professor
                .builder()
                .nome(request.getNome())
                .siape(request.getSiape())
                .email(request.getEmail())
                .celular(request.getCelular())
                .build();
    }
}
