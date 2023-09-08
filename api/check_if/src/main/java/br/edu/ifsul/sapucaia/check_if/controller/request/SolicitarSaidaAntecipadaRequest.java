package br.edu.ifsul.sapucaia.check_if.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SolicitarSaidaAntecipadaRequest {

    @NotNull
    private LocalDate dataAutorizada;

    @NotNull
    private LocalTime horaAutorizada;

    @NotBlank
    private String motivo;

    @NotNull
    private Long idAluno;
}
