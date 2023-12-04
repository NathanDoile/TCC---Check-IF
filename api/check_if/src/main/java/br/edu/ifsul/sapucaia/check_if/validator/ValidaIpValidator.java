package br.edu.ifsul.sapucaia.check_if.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class ValidaIpValidator {

    public void validar(HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        System.out.println(ip);
        List<String> valoresIp = stream(ip.split("\\.")).toList();

        int terceiroValorIp = parseInt(valoresIp.get(2));

        int quartoValorIp = parseInt(valoresIp.get(2));

        if(!ip.startsWith("172.16.") ||
                (terceiroValorIp < 0 || terceiroValorIp > 15 ) ||
                (quartoValorIp < 0 || quartoValorIp > 255 )){
            throw new ResponseStatusException(UNAUTHORIZED, "É necessário estar conectado à rede do Campus Sapucaia.");
        }
    }
}
