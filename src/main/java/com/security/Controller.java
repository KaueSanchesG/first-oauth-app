package com.security;

import com.security.user.IUserRepository;
import com.security.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
    @Autowired
    private IUserRepository repository;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1>Teste</h1>";
    }

    @GetMapping("/livre")
    public String livre(){
        return "<h1>Acesso livre</h1>";
    }

    @PostMapping("/user/create")
    public String create(UserModel user){
        try {
            this.repository.save(user);
            return "Criado com sucesso";
        }catch (RuntimeException e){
            return "Erro " + e.getMessage();
        }
    }
}

