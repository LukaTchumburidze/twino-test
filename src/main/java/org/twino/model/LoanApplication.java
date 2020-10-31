package org.twino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Random;

@Entity
@Data
public class LoanApplication {

    public enum Term {
        DAYS,
        MONTHS
    }

    public enum Verdict {
        APPROVED,
        MANUAL,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(updatable = false)
    private String firstName;
    @NotEmpty
    @Column(updatable = false)
    private String lastName;
    @NotNull
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    @Column(updatable = false)
    private Double salary;
    @NotNull
    @Column(updatable = false)
    private Double monthlyLiability;

    @NotNull
    @Column(updatable = false)
    private Double requestedAmount;
    @Enumerated
    @Column(updatable = false)
    private Term requestedTerm;

    @Enumerated
    private Verdict verdict;


    @Column(updatable = false)
    private Double score;

    @PrePersist
    private void calculateScore () {
        this.score = salary;
        // Actual score logic here

        this.verdict = Verdict.REJECTED;

        if (this.score <= 3500) {
            this.verdict = Verdict.MANUAL;

            if (this.score < 2500) {
                this.verdict = Verdict.APPROVED;
            }
        }
    }
}
