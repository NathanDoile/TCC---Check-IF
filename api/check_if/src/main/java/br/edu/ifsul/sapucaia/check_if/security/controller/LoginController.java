package br.edu.ifsul.sapucaia.check_if.security.controller;

import br.edu.ifsul.sapucaia.check_if.security.controller.response.UsuarioResponse;
import br.edu.ifsul.sapucaia.check_if.security.service.BuscarUsuarioLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BuscarUsuarioLoginService buscarUsuarioLoginService;

    @PostMapping
    public String login(){
        return "ENTROU";
        //return buscarUsuarioLoginService.buscar();
    }
}
