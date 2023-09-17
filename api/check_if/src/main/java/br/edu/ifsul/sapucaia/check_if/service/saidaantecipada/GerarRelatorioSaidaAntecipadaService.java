package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import com.aspose.pdf.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.aspose.pdf.BorderSide.All;
import static com.aspose.pdf.Color.getBlack;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class GerarRelatorioSaidaAntecipadaService {

    public void gerar(Document doc, List<SaidaAntecipada> saidasAntecipadas, Aluno aluno) {

        doc.getPages().add();
        doc.getPages().get_Item(1).getPageInfo().setMargin(new MarginInfo(15, 20.0, 15, 20.0));

        Table table = new Table();

        table.setColumnWidths("80");

        table.setBorder(new BorderInfo(All, 1F, getBlack()));

        table.setDefaultCellBorder(new BorderInfo(All, 0.1F, getBlack()));

        MarginInfo margem = new MarginInfo();
        margem.setTop(5f);
        margem.setLeft(5f);
        margem.setRight(5f);
        margem.setBottom(5f);

        table.setDefaultCellPadding(margem);

        Row cabecalho = table.getRows().add();

        cabecalho.getCells().add("NOME");
        cabecalho.getCells().add("MATRÍCULA");
        cabecalho.getCells().add("TURMA");
        cabecalho.getCells().add("DATA");
        cabecalho.getCells().add("HORA");
        cabecalho.getCells().add("OCORRÊNCIA");
        cabecalho.getCells().add("MOTIVO");

        for(SaidaAntecipada saidaAntecipada : saidasAntecipadas){

            Row linha = table.getRows().add();

            linha.getCells().add(aluno.getNome());
            linha.getCells().add(aluno.getMatricula());
            linha.getCells().add(aluno.getTurma());
            linha.getCells().add(saidaAntecipada.getDataHoraSaida().toLocalDate().format(ofPattern("dd/MM/yyyy")).toString());
            linha.getCells().add(saidaAntecipada.getDataHoraSaida().toLocalTime().toString());
            linha.getCells().add("SAÍDA ANTECIPADA");
            linha.getCells().add(saidaAntecipada.getMotivo());
        }

        doc.getPages().get_Item(1).getParagraphs().add(table);
    }
}
