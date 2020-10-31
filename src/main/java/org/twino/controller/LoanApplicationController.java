package org.twino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.twino.model.CustomSort;
import org.twino.model.LoanApplication;
import org.twino.model.Operator;
import org.twino.repository.LoanApplicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/loan")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @PostMapping
    private String loanApplicationRegistration (Model model, @ModelAttribute("loan") LoanApplication loanApplication) {
        loanApplicationRepository.save(loanApplication);

        return "index.html";
    }

    @GetMapping
    private String getLoanPage () {
        return "newloan.html";
    }

    @GetMapping(path = "/list")
    private String getListOfLoans (Model model) {
        List<LoanApplication> loanApplicationList = new ArrayList<>();
        loanApplicationRepository.findAll().forEach(loanApplicationList::add);
        model.addAttribute("loanApplicationList", loanApplicationList);

        return "loanlist.html";
    }

    @PostMapping(path = "/list")
    private String submitSortForLoanList (Model model,
                                   @ModelAttribute("customSort")CustomSort customSort) {

        List<LoanApplication> loanApplicationList = new ArrayList<>();

        if (customSort.getOrder().equals("ASC")) {
            loanApplicationRepository.findAll(Sort.by(customSort.getField()).ascending()).forEach(loanApplicationList::add);
        } else {
            loanApplicationRepository.findAll(Sort.by(customSort.getField()).descending()).forEach(loanApplicationList::add);
        }

        model.addAttribute("loanApplicationList", loanApplicationList);

        return "loanlist.html";
    }
}
