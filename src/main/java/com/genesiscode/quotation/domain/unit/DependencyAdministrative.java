package com.genesiscode.quotation.domain.unit;
import lombok.*;
import javax.persistence.*;

@Setter @Getter @ToString @NoArgsConstructor
@Entity @Table(name = "dependencies_administratives")
public class DependencyAdministrative {

    @Id
    @SequenceGenerator(name = "dependency_admin_sequence", sequenceName = "dependency_admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependency_admin_sequence")
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_administrative", nullable = false, updatable = false)
    private AdministrativeUnit unit;

    public DependencyAdministrative(String name) {
        this.name = name;
    }
}
