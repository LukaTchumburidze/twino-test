package org.twino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.twino.model.Operator;
import org.twino.repository.OperatorRepository;

@Controller
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private OperatorRepository operatorRepository;

    @PostMapping
    private String operatorRegistration (@ModelAttribute("operator") Operator operator) {
        if (!operator.getPassword().equals(operator.getConfirmPassword())) {
            return "register.html";
        }

        operatorRepository.save(operator);

        return "index.html";
    }

    @GetMapping
    private String getRegistrationPage () {
        return "register.html";
    }
}
