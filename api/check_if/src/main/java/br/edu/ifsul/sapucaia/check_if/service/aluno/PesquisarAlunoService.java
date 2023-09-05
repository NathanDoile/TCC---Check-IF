package br.edu.ifsul.sapucaia.check_if.service.aluno;

import br.edu.ifsul.sapucaia.check_if.controller.response.PesquisarAlunoResponse;
import br.edu.ifsul.sapucaia.check_if.domain.Aluno;
import br.edu.ifsul.sapucaia.check_if.mapper.AlunoMapper;
import br.edu.ifsul.sapucaia.check_if.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static br.edu.ifsul.sapucaia.check_if.mapper.AlunoMapper.toPesquisarAlunoResponse;

@Service
public class PesquisarAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Page<PesquisarAlunoResponse> pesquisar(String texto, Pageable pageable) {

        Page<Aluno> alunos = alunoRepository.findByNomeContainingOrMatriculaContaining(texto, texto, pageable);

        if(alunos.isEmpty()){
            return null;
        }

        return alunos.map(AlunoMapper::toPesquisarAlunoResponse);
    }
}
