package br.edu.ifsul.sapucaia.check_if.service.permissao;

import br.edu.ifsul.sapucaia.check_if.repository.PermissaoRepository;
import br.edu.ifsul.sapucaia.check_if.security.domain.Enum.Funcao;
import br.edu.ifsul.sapucaia.check_if.security.domain.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarPermissaoResponsavelService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao cadastrar() {

        Permissao permissao = Permissao
                .builder()
                .funcao(Funcao.RESPONSAVEL)
                .build();

        permissaoRepository.save(permissao);

        return permissao;
    }
}
