package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
public class CadastrarResponsavelRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Long celular;

    @NotNull
    @NotEmpty
    private List<Long> idAlunos;

}
