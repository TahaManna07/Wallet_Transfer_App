package ma.enset.walletservice.web;

import lombok.AllArgsConstructor;
import ma.enset.walletservice.entities.Client;
import ma.enset.walletservice.entities.Wallet;
import ma.enset.walletservice.repositories.ClientRepository;
import ma.enset.walletservice.repositories.WalletRepository;
import ma.enset.walletservice.services.WalletService;
import ma.enset.walletservice.services.WalletUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/wallets") // Regrouper les endpoints sous un seul chemin
public class WalletController {
    private final WalletRepository walletRepository;
    private final ClientRepository clientRepository;
    private final WalletService walletService;

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable("id") String id) {
        return walletRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build()); // Retourne une réponse 404 si le portefeuille n'est pas trouvé
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Indique que la création a réussi
    public Wallet createWallet(@RequestBody Wallet wallet) {
        if (wallet.getClient() != null && wallet.getClient().getId() != null) {
            Client client = clientRepository.findById(String.valueOf(wallet.getClient().getId()))
                    .orElseThrow(() -> new RuntimeException("Client non trouvé"));
            wallet.setClient(client); // Associer le client au portefeuille
        }
        return walletRepository.save(wallet);
    }

    @PutMapping("/update")
    public Wallet updateWalletBalance(@RequestBody WalletUpdateRequest request) {
        // Appeler le service pour mettre à jour le solde du wallet
        return walletService.updateWalletBalance(request);
    }

}
