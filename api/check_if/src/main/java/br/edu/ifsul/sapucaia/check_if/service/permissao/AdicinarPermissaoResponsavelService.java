package br.edu.ifsul.sapucaia.check_if.service.permissao;

import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.sapucaia.check_if.security.domain.Enum.Funcao.RESPONSAVEL;

@Service
public class AdicinarPermissaoResponsavelService {

    public void adicionar(Responsavel responsavel) {

        Permissao permissao = Permissao
                .builder()
                .funcao(RESPONSAVEL)
                .build();

        responsavel.adicionarPermissao(permissao);
    }
}
