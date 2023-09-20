package br.edu.ifsul.sapucaia.check_if.service.validator;

import br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida;
import br.edu.ifsul.sapucaia.check_if.repository.SaidaAntecipadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.edu.ifsul.sapucaia.check_if.domain.enums.SituacaoSaida.PENDENTE;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ValidaSaidaAntecipadaService {

    @Autowired
    private SaidaAntecipadaRepository saidaAntecipadaRepository;

    public void porid(Long id) {

        if(!saidaAntecipadaRepository.existsById(id)){

            throw new ResponseStatusException(NOT_FOUND, "Saída antecipada não encontrada.");
        }
    }

    public void pendente(SituacaoSaida situacaoSaida) {

        if(situacaoSaida != PENDENTE){

            throw new ResponseStatusException(CONFLICT, "A saída já foi confirmada.");
        }
    }
}
