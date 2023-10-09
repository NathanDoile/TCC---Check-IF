package br.edu.ifsul.sapucaia.check_if.service.responsavel;

import br.edu.ifsul.sapucaia.check_if.controller.request.responsavel.AlterarResponsavelRequest;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.repository.ResponsavelRepository;
import br.edu.ifsul.sapucaia.check_if.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

        validaResponsavelService.porCelular(request.getCelular());
        validaResponsavelService.porEmail(request.getEmail());

        Responsavel responsavel = usuarioAutenticadoService.getResponsavel();

        responsavel.setNome(request.getNome());
        responsavel.setEmail(request.getEmail());
        responsavel.setCelular(request.getCelular());

        responsavelRepository.save(responsavel);
    }
}
