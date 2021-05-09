package com.genesiscode.quotation.registration.token;
import com.genesiscode.quotation.domain.user.Responsible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @NoArgsConstructor
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "responsible_id")
    private Responsible responsible;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt,
                             Responsible responsible) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.responsible = responsible;
    }
}
