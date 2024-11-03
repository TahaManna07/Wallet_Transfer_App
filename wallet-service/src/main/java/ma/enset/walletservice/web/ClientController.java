package ma.enset.walletservice.web;

import ma.enset.walletservice.entities.Client;
import ma.enset.walletservice.entities.Wallet;
import ma.enset.walletservice.repositories.ClientRepository;
import ma.enset.walletservice.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private WalletRepository walletRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientRepository.save(client);
        return ResponseEntity.ok(createdClient);
    }

    @PostMapping("/clients/{clientId}/wallets")
    public ResponseEntity<Wallet> createWallet(@PathVariable Long clientId, @RequestBody Wallet wallet) {
        return clientRepository.findById(String.valueOf(clientId))
                .map(client -> {
                    wallet.setClient(client); // Lier le portefeuille au client
                    Wallet createdWallet = walletRepository.save(wallet);
                    return ResponseEntity.ok(createdWallet);
                }).orElse(ResponseEntity.notFound().build());
    }
}
