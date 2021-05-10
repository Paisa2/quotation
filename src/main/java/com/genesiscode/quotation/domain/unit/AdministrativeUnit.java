package com.genesiscode.quotation.domain.unit;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
@Entity @Table(name = "units_administratives")
public class AdministrativeUnit {

    @Id
    @SequenceGenerator(name = "unit_admin_sequence", sequenceName = "unit_admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_admin_sequence")
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "unit")
    private List<DependencyAdministrative> dependency;

    public AdministrativeUnit(String name) {
        this.name = name;
    }

}
