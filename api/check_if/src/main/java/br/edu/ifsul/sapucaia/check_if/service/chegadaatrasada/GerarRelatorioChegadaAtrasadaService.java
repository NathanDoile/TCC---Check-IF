package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.domain.Professor;
import br.edu.ifsul.sapucaia.check_if.repository.ProfessorRepository;
import com.aspose.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.aspose.pdf.BorderSide.All;
import static com.aspose.pdf.Color.getBlack;

@Service
public class GerarRelatorioChegadaAtrasadaService {

    @Autowired
    private ProfessorRepository professorRepository;

    public void gerar(Document doc, List<ChegadaAtrasada> chegadasAtrasadas, Aluno aluno) {

        doc.getPages().add();
        doc.getPages().get_Item(2).getPageInfo().setMargin(new MarginInfo(15, 20.0, 15, 20.0));

        Table table = new Table();

        table.setColumnWidths("80 80 50 70 60 80 80 60");

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
        cabecalho.getCells().add("PROFESSOR");
        cabecalho.getCells().add("MOTIVO");

        for(ChegadaAtrasada chegadaAtrasada : chegadasAtrasadas){

            Professor professor = professorRepository.findByChegadasAtrasadas(chegadaAtrasada);

            Row linha = table.getRows().add();

            linha.getCells().add(aluno.getNome());
            linha.getCells().add(aluno.getMatricula());
            linha.getCells().add(aluno.getTurma());
            linha.getCells().add(chegadaAtrasada.getDataHora().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
            linha.getCells().add(chegadaAtrasada.getDataHora().toLocalTime().toString());
            linha.getCells().add("CHEGADA ATRASADA");
            linha.getCells().add(professor.getNome());
            linha.getCells().add(chegadaAtrasada.getMotivo());
        }

        doc.getPages().get_Item(2).getParagraphs().add(table);
    }
}
