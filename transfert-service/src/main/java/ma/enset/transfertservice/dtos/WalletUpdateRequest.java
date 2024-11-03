package ma.enset.transfertservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletUpdateRequest {
    private String walletId; // ID du portefeuille
    private double newBalance; // Nouveau solde
}