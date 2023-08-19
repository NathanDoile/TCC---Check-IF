package br.edu.ifsul.sapucaia.check_if.security.config;

import br.edu.ifsul.sapucaia.check_if.domain.Administrador;
import br.edu.ifsul.sapucaia.check_if.domain.Portaria;
import br.edu.ifsul.sapucaia.check_if.domain.Responsavel;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UsuarioSecurity implements UserDetails {

    private Long id;
    private String email;
    private String senha;
    private boolean ativo;
    private List<SimpleGrantedAuthority> permissoes;

    public UsuarioSecurity(Administrador usuario) {

        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.ativo = usuario.isAtivo();
    }

    public UsuarioSecurity(Portaria usuario) {

        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.ativo = usuario.isAtivo();
    }

    public UsuarioSecurity(Responsavel usuario) {

        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.ativo = usuario.isAtivo();
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.ativo;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.ativo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.ativo;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}