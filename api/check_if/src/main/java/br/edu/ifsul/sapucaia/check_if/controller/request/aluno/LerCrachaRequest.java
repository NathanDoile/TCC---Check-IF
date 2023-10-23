package br.edu.ifsul.sapucaia.check_if.controller.request.aluno;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.File;

@Getter
public class LerCrachaRequest {

    @NotNull
    private File fotoCracha;
}
