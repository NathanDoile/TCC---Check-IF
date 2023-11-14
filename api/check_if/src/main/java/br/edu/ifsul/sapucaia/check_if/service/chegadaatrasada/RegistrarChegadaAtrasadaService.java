package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.controller.request.chegadaatrasada.RegistrarChegadaAtrasadaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.*;
import br.edu.ifsul.sapucaia.check_if.repository.*;
import br.edu.ifsul.sapucaia.check_if.security.controller.request.EnviarEmailRequest;
import br.edu.ifsul.sapucaia.check_if.security.service.EnviarEmailService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaAlunoService;
import br.edu.ifsul.sapucaia.check_if.validator.ValidaIpValidator;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaProfessorService;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toEntity;
import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toResponse;
import static com.aspose.barcode.barcoderecognition.DecodeType.CODE_39_STANDARD;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
public class RegistrarChegadaAtrasadaService {

    @Autowired
    private ChegadaAtrasadaRepository chegadaAtrasadaRepository;

    @Autowired
    private ValidaAlunoService validaAlunoService;

    @Autowired
    private ValidaProfessorService validaProfessorService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private NotificacaoEmailRepository notificacaoEmailRepository;

    @Autowired
    private NotificacaoWhatsappRepository notificacaoWhatsappRepository;

    @Autowired
    private ValidaIpValidator validaIpValidator;

    @Transactional
    public ChegadaAtrasadaResponse registrar(RegistrarChegadaAtrasadaRequest request, HttpServletRequest requestServlet) throws IOException {

        validaAlunoService.porMatricula(request.getMatriculaAluno());
        validaProfessorService.porId(request.getIdProfessor());
        validaIpValidator.validar(requestServlet);

        Aluno aluno = alunoRepository.findByMatricula(request.getMatriculaAluno());

        Professor professor = professorRepository.findById(request.getIdProfessor()).get();

        ChegadaAtrasada chegadaAtrasada = toEntity(request);
        chegadaAtrasada.setDataHora(now());
        chegadaAtrasada.setAluno(aluno);
        chegadaAtrasada.setProfessor(professor);

        chegadaAtrasadaRepository.save(chegadaAtrasada);

        List<Responsavel> responsaveis = responsavelRepository.findAllByAlunos(aluno);

        EnviarEmailRequest enviarEmailRequest = EnviarEmailRequest
                .builder()
                .titulo("Chegada Atrasada do aluno " + aluno.getNome())
                .mensagem(mensagemChegadaAtrasada(chegadaAtrasada))
                .build();

        for(Responsavel responsavel : responsaveis){

            NotificacaoEmail notificacaoEmail = notificacaoEmailRepository.findByAlunoAndResponsavel(aluno, responsavel);

            if(notificacaoEmail.isReceber()){

                enviarEmailService.enviarResponsavel(enviarEmailRequest, responsavel);
            }

            NotificacaoWhatsapp notificacaoWhatsapp = notificacaoWhatsappRepository.findByAlunoAndResponsavel(aluno, responsavel);

            if(notificacaoWhatsapp.isReceber()){


            }
        }

        if(professor.isNotificacaoEmail()){

            EnviarEmailRequest enviarEmailRequestProfessor = EnviarEmailRequest
                    .builder()
                    .titulo("Chegada Atrasada do aluno " + aluno.getNome())
                    .mensagem(mensagemChegadaAtrasadaProfessor(chegadaAtrasada))
                    .build();

            enviarEmailService.enviarProfessor(enviarEmailRequestProfessor, professor);
        }

        return toResponse(chegadaAtrasada);
    }

    private String mensagemChegadaAtrasada(ChegadaAtrasada chegadaAtrasada) {

        return "Prezado(a),\n\n" +
                "Informamos que o aluno " + chegadaAtrasada.getAluno().getNome() + " registrou uma chegada atrasada no " +
                "dia " + chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                chegadaAtrasada.getDataHora().toLocalTime().format(ofPattern("HH:mm:ss")) + ", na disciplina " + chegadaAtrasada.getDisciplina()
                + " e alegou o seguinte motivo: '" + chegadaAtrasada.getMotivo() + "'.\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }

    private String mensagemChegadaAtrasadaProfessor(ChegadaAtrasada chegadaAtrasada) {

        return "Prezado(a),\n\n" +
                "Informamos que o aluno " + chegadaAtrasada.getAluno().getNome() + ", da turma " + chegadaAtrasada.getAluno().getTurma() + ", " +
                "registrou uma chegada atrasada no " +
                "dia " + chegadaAtrasada.getDataHora().toLocalDate().format(ofPattern("dd/MM/yyyy")) + ", às " +
                chegadaAtrasada.getDataHora().toLocalTime().format(ofPattern("HH:mm:ss")) + ", na disciplina " + chegadaAtrasada.getDisciplina()
                + " e alegou o seguinte motivo: '" + chegadaAtrasada.getMotivo() + "'.\n\n" +
                "Atenciosamente,\n" +
                "Check-IF";
    }
}
