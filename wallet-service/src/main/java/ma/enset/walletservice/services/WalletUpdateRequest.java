package ma.enset.walletservice.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletUpdateRequest {
    private String walletId;
    private double amount;
}
