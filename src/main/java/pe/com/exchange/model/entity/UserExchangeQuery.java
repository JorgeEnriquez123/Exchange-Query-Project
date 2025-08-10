package pe.com.exchange.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class UserExchangeQuery {
    @Id
    private String dni;
    private int queryAttempts;

    public void incrementQueryAttempts() {
        this.queryAttempts++;
    }
}
