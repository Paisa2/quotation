package com.genesiscode.quotation.domain.user;
import com.genesiscode.quotation.security.RoleResponsible;
import lombok.*;
import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor @EqualsAndHashCode
@Entity @Table(name = "responsibles")
public class Responsible {

    @Id
    @SequenceGenerator(name = "responsible_sequence", sequenceName = "responsible_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsible_sequence")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleResponsible role;

    public Responsible(String name, String lastName, String email, RoleResponsible role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

}
