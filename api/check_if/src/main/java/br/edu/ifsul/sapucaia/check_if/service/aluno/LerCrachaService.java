package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.request.aluno.LerCrachaRequest;
import br.edu.ifsul.sapucaia.check_if.controller.response.CrachaResponse;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.BufferedImage;
import java.io.*;

import static com.aspose.barcode.barcoderecognition.DecodeType.CODE_128;
import static java.util.Objects.isNull;
import static javax.imageio.ImageIO.read;
import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class LerCrachaService {

    public CrachaResponse ler(LerCrachaRequest request) throws IOException {

        byte[] imageBytes = parseBase64Binary(request.getFotoCracha());

        FileOutputStream fos = new FileOutputStream("cracha.jpg");
        try{
            fos.write(imageBytes);
        }finally {
            fos.close();
        }

        BufferedImage img = read(new File("Sample 11-14-2023 09.29_2(2).jpg"));
        //BufferedImage img = read(new File("cracha.jpg"));

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
            System.out.println(matricula);
            return CrachaResponse
                    .builder()
                    .matricula(matricula)
                    .build();
        }
    }
}
