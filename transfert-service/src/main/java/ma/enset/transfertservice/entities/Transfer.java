package ma.enset.transfertservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.transfertservice.enums.TransferStatus;
import ma.enset.transfertservice.model.Wallet;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    private String sourceWallet; // ID du portefeuille source
    private String destinationWallet; // ID du portefeuille destination

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @Transient
    private Wallet walletSource; // Représente le portefeuille source comme objet transitoire

    @Transient
    private Wallet walletDestination; // Représente le portefeuille destination comme objet transitoire
}
