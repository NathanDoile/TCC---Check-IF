package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class AlterarResponsavelService {


    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ValidaResponsavelService validaResponsavelService;

    @Transactional
    public void alterar(AlterarResponsavelRequest request) {

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        if(!Objects.equals(responsavel.getCelular(), request.getCelular()) && request.getCelular() != null && request.getCelular() <= 0){
            validaResponsavelService.porCelular(request.getCelular());
        }

        if(!responsavel.getEmail().equalsIgnoreCase(request.getEmail())){
            validaResponsavelService.porEmail(request.getEmail());
        }

        responsavel.setNome(request.getNome());
        responsavel.setEmail(request.getEmail());
        responsavel.setCelular(request.getCelular());

        responsavelRepository.save(responsavel);
    }
}
