package com.genesiscode.quotation.domain;
import com.genesiscode.quotation.domain.Responsible;
import lombok.*;
import javax.persistence.*;
import java.time.*;

@Setter @Getter @NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;

    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;

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
