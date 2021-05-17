package com.genesiscode.quotation.domain;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Setter @Getter @ToString @NoArgsConstructor
@Entity
public class Permission {

    @Id
    @Column(name = "permission_id")
    @SequenceGenerator(name = "permission_sequence", sequenceName = "permission_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_sequence")
    private Long id;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> role;

    @Enumerated(EnumType.STRING)
    @Column(name = "permissions", unique = true)
    private Functionality functionality;

    public Permission(Functionality functionality) {
        this.functionality = functionality;
    }

}
