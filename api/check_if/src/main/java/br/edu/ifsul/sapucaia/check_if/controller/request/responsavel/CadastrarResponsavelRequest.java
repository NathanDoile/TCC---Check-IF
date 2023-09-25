package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarResponsavelRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    private Long celular;

    @NotNull
    @NotEmpty
    private List<Long> idAlunos;

}
