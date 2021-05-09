package com.genesiscode.quotation.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter @Getter @ToString @NoArgsConstructor
public class ExpenseUnit {

    @Id
    @SequenceGenerator(name = "expense_unit_sequence", sequenceName = "expense_unit_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expense_unit_sequence")
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    public ExpenseUnit(String name) {
        this.name = name;
    }

}
