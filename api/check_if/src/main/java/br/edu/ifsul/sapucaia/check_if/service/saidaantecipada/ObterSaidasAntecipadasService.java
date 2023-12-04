package br.edu.ifsul.sapucaia.check_if.service.saidaantecipada;

import br.edu.ifsul.sapucaia.check_if.controller.response.SaidaAntecipadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.SaidaAntecipada;
import br.edu.ifsul.sapucaia.check_if.mapper.SaidaAntecipadaMapper;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.PENDENTE;
import static java.time.LocalDate.now;
import static java.time.LocalTime.MAX;
import static java.time.LocalTime.MIN;

@Service
public class ObterSaidasAntecipadasService {

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    public Page<SaidaAntecipadaResponse> obter(Pageable pageable) {

        LocalDateTime inicioData = LocalDateTime.of(now(), MIN);
        LocalDateTime fimData = LocalDateTime.of(now(), MAX);

        Page<SaidaAntecipada> saidasAntecipadas = saidaAntecipadaRepository.
                findAllByDataHoraAutorizadaBetweenAndSituacaoSaidaOrderByDataHoraAutorizada
                        (inicioData, fimData, PENDENTE, pageable);

        return saidasAntecipadas.map(SaidaAntecipadaMapper::toResponse);
    }
}
