package org.twino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.twino.model.Operator;
import org.twino.repository.OperatorRepository;

@Controller
public class LoginController {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String login () {
        return "login.html";
    }

    @PostMapping(value = "/login")
    public String login (@ModelAttribute("operator")Operator operator) {

        if (operatorRepository.findByUsername(operator.getUsername()) == null) {
            return "login.html";
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (operator.getUsername(),
                        operator.getPassword())));


        return "index.html";
    }

}
