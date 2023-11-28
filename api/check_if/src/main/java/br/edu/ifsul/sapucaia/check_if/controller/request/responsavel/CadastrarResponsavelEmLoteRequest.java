package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import com.aspose.cells.Workbook;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;

@Getter
public class CadastrarResponsavelEmLoteRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    @NotNull(message = MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO)
    private MultipartFile arquivo;
}
