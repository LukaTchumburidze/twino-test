package org.twino.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private Date birthDate;

    @NotEmpty
    private double salary;
    @NotEmpty
    private double monthlyLiability;

    @NotEmpty
    private double requestedAmount;
    @NotEmpty
    @Enumerated
    private Term requestedTerm;

    @Enumerated
    private Verdict verdict;

    @JsonIgnore
    private double score;

    @PrePersist
    private void calculateScore () {
        this.score = 5;
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
