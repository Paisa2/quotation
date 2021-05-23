package com.genesiscode.quotation.domain;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor @Getter @Setter @ToString
@Entity
public class Responsible /*implements UserDetails */{

    @Id
    @Column(name = "responsible_id")
    @SequenceGenerator(name = "responsible_sequence", sequenceName = "responsible_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsible_sequence")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToOne
    @JoinColumn(name= "role_id")
    private Role role;

    public Responsible(String name, String lastName, String email, String password, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

/*

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
*/

}
