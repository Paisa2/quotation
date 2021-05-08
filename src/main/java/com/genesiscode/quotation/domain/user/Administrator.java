package com.genesiscode.quotation.domain.user;
import lombok.*;
import javax.persistence.*;

@Setter @Getter @ToString @NoArgsConstructor @EqualsAndHashCode
@Entity
public class Administrator {

    @Id
    @SequenceGenerator(name = "administrator_sequence", sequenceName = "administrator_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "administrator_sequence")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
