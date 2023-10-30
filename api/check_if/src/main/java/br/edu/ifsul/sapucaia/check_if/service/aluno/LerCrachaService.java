package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.request.aluno.LerCrachaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.CrachaResponse;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.imaging.Image;
import com.aspose.imaging.ImageOptionsBase;
import com.aspose.imaging.imageoptions.JpegOptions;
import com.aspose.imaging.imageoptions.PngOptions;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static com.aspose.imaging.Image.load;
import static java.util.Objects.isNull;
import static javax.imageio.ImageIO.read;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class LerCrachaService {

    public CrachaResponse ler(LerCrachaRequest request) throws IOException {

//        Image imagem = load(new FileInputStream(request.getFotoCracha()));
//
//        ImageOptionsBase exportOptions = new JpegOptions();
//
//        imagem.save("cracha.jpg", exportOptions);

        FileOutputStream arquivo = new FileOutputStream("cracha1.jpg");
        arquivo.write(request.getFotoCracha());

        BufferedImage img = read(new File("cracha1.jpg"));

        BarCodeReader reader = new BarCodeReader(img);

        String matricula = null;

        for(BarCodeResult result : reader.readBarCodes()){

            matricula = result.getCodeText().replace(
                            "Recognized by Aspose Barcode Reader evaluation version. Only Code39Standard can be recognized without " +
                                    "restrictions. Please buy license to use Aspose Barcode Reader without watermarks.", "")
                    .replace("<FNC1>", "");
        }

        if(isNull(matricula)){
            throw new ResponseStatusException(BAD_REQUEST, "Nenhum código de barras encontrado!");
        }
        else{

            return CrachaResponse
                    .builder()
                    .matricula(matricula)
                    .build();
        }
    }
}
