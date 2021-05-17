package com.genesiscode.quotation.domain;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter @Setter @ToString @NoArgsConstructor
@Entity
public class Role {

    @Id
    @Column(name = "role_id")
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"))
    private List<Permission> permission;

    public Role(String name, List<Permission> permission) {
        this.name = name;
        this.permission = permission;
    }
}
