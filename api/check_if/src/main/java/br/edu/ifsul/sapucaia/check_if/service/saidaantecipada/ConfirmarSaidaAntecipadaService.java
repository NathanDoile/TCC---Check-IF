package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.request.saidaantecipada.ConfirmarSaidaAntecipadaRequest;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import br.edu.ifsul.sapucaia.check_if.service.validator.ValidaSaidaAntecipadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.*;
import static java.time.LocalDateTime.now;

@Service
public class ConfirmarSaidaAntecipadaService {

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    @Autowired
    private ValidaSaidaAntecipadaService validaSaidaAntecipadaService;

    @Transactional
    public void confirmar(ConfirmarSaidaAntecipadaRequest request) {

        validaSaidaAntecipadaService.porid(request.getId());

        SaidaAntecipada saidaAntecipada = saidaAntecipadaRepository.findById(request.getId()).get();

        validaSaidaAntecipadaService.pendente(saidaAntecipada.getSituacaoSaida());

        if(request.isSaiu()){
            saidaAntecipada.setSituacaoSaida(SAIU);

            saidaAntecipada.setDataHoraSaida(now());
        }
        else{
            saidaAntecipada.setSituacaoSaida(NÃO_COMPARECEU);
        }

        saidaAntecipadaRepository.save(saidaAntecipada);
    }
}
