package br.edu.ifsul.sapucaia.check_if.security.domain.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Funcao {

    PORTARIA(Nomes.PORTARIA),
    ADMINISTRADOR(Nomes.ADMINISTRADOR),
    RESPONSAVEL(Nomes.RESPONSAVEL);

    public static class Nomes {

        private Nomes() {
        }

        public static final String PORTARIA = "ROLE_PORTARIA";
        public static final String ADMINISTRADOR = "ROLE_ADMINISTRADOR";
        public static final String RESPONSAVEL = "ROLE_RESPONSAVEL";
    }

    private final String role;
}
