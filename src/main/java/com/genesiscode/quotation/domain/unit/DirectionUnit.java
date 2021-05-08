package com.genesiscode.quotation.domain.unit;
import lombok.*;
import javax.persistence.*;

@Setter @Getter @ToString @NoArgsConstructor
@Entity @Table(name = "direction_units")
public class DirectionUnit {

    @Id
    @SequenceGenerator(name = "unit_direction_sequence", sequenceName = "unit_direction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_direction_sequence")
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    public DirectionUnit(String name) {
        this.name = name;
    }
}
