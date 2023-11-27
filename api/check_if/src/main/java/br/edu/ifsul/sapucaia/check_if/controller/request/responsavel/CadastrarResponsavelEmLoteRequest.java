package br.edu.ifsul.sapucaia.check_if.controller.request.responsavel;

import com.aspose.cells.Workbook;
import lombok.Getter;

@Getter
public class CadastrarResponsavelEmLoteRequest {

    public static final String MENSAGEM_DE_ERRO_CAMPO_OBRIGATORIO = "é um campo obrigatório";

    private File arquivo;
}
