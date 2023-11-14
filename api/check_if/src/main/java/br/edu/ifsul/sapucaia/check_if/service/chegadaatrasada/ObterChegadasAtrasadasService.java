package br.edu.ifsul.sapucaia.check_if.service.chegadaatrasada;

import br.edu.ifsul.sapucaia.check_if.controller.response.ChegadaAtrasadaResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.domain.ChegadaAtrasada;
import br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import br.edu.ifsul.sapucaia.check_if.repository.ChegadaAtrasadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static br.edu.ifsul.sapucaia.check_if.mapper.ChegadasAtrasadasMapper.toResponse;

@Service
public class ObterChegadasAtrasadasService {

    @Autowired
    private ChegadaAtrasadaRepository chegadaAtrasadaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public Page<ChegadaAtrasadaResponse> obter(String data, Pageable pageable) {

        LocalDate date = LocalDate.parse(data);

        LocalDateTime inicioData = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime fimData = LocalDateTime.of(date, LocalTime.MAX);

        Page<ChegadaAtrasada> chegadasAtrasadas = chegadaAtrasadaRepository.findByDataHoraBetween(inicioData,
                fimData, pageable);

        return chegadasAtrasadas.map(ChegadasAtrasadasMapper::toResponse);
    }
}
