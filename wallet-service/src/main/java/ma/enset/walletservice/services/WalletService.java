package ma.enset.walletservice.services;

import ma.enset.walletservice.entities.Wallet;
import ma.enset.walletservice.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public Wallet updateWalletBalance(WalletUpdateRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Définir le solde directement à partir de newBalance
        wallet.setBalance(request.getAmount());

        // Sauvegarder et retourner le wallet mis à jour
        return walletRepository.save(wallet);
    }

}


