package ma.enset.walletservice.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    private String id; // UUID
    private double balance;
    private Date createdAt;
    private String currency;

    @ManyToOne
     // FK vers Client
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // Ne pas inclure dans les réponses
    private Client client;

}
