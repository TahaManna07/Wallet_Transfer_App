package ma.enset.transfertservice.web;

import lombok.AllArgsConstructor;
import ma.enset.transfertservice.clients.WalletRestClient;
import ma.enset.transfertservice.entities.Transfer;
import ma.enset.transfertservice.enums.TransferStatus;
import ma.enset.transfertservice.repositories.TransferRepository;
import ma.enset.transfertservice.dtos.WalletUpdateRequest; // Assurez-vous que ce DTO est défini
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class TransferController {
    private final TransferRepository transferRepository;
    private final WalletRestClient walletRestClient;

    // Récupérer tous les transferts
    @GetMapping("/transfers")
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    // Récupérer un transfert par ID
    @GetMapping("/transfers/{id}")
    public Transfer getTransferById(@PathVariable Long id) {
        return transferRepository.findById(id).orElse(null);
    }

    // Créer un nouveau transfert
    @PostMapping("/addTransfer")
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        transfer.setDate(new Date());
        transfer.setStatus(TransferStatus.PENDING);
        // Récupérer les informations des portefeuilles via le client Feign
        var walletSource = walletRestClient.getWalletById(transfer.getSourceWallet());
        var walletDestination = walletRestClient.getWalletById(transfer.getDestinationWallet());
        // Vérifier les soldes avant d'effectuer le transfert
        if (walletSource.getBalance() < transfer.getAmount()) {
            throw new IllegalArgumentException("Le solde du portefeuille source est insuffisant");
        }

        // Mettre à jour les soldes des portefeuilles
        double newSourceBalance = walletSource.getBalance() - transfer.getAmount();
        double newDestinationBalance = walletDestination.getBalance() + transfer.getAmount();
        System.out.println(walletSource.getBalance() + " .....  "+walletDestination.getBalance());

        // Créer une requête de mise à jour pour le portefeuille source
        WalletUpdateRequest sourceUpdate = new WalletUpdateRequest(walletSource.getId(), newSourceBalance);
        walletRestClient.updateWalletBalance(sourceUpdate);

        // Créer une requête de mise à jour pour le portefeuille destination
        WalletUpdateRequest destinationUpdate = new WalletUpdateRequest(walletDestination.getId(), newDestinationBalance);
        walletRestClient.updateWalletBalance(destinationUpdate);
        // Sauvegarder le transfert dans la base de données
        return transferRepository.save(transfer);
    }
}
