package com.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String teste(){
        return "<h1>Teste</h1>";
    }

    @GetMapping("/livre")
    public String livre(){
        return "<h1>Acesso livre</h1>";
    }
}

