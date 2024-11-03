package ma.enset.walletservice;

import lombok.AllArgsConstructor;
import ma.enset.walletservice.entities.Client;
import ma.enset.walletservice.entities.Wallet;
import ma.enset.walletservice.repositories.ClientRepository;
import ma.enset.walletservice.repositories.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@AllArgsConstructor
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    private final WalletRepository walletRepository;
    private final ClientRepository clientRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            // Création des clients
            Client client1 = Client.builder()
                    .nom("John Doe")
                    .email("john.doe@example.com")
                    .build();
            Client client2 = Client.builder()
                    .nom("Jane Smith")
                    .email("jane.smith@example.com")
                    .build();

            clientRepository.save(client1);
            clientRepository.save(client2);

            // Création des portefeuilles associés aux clients
            Wallet wallet1 = Wallet.builder()
                    .id(UUID.randomUUID().toString())
                    .balance(1500.15)
                    .createdAt(new Date())
                    .currency("USD")
                    .client(client1) // Association du portefeuille au client
                    .build();
            Wallet wallet2 = Wallet.builder()
                    .id(UUID.randomUUID().toString())
                    .balance(4050.05)
                    .createdAt(new Date())
                    .currency("DH")
                    .client(client2) // Association du portefeuille au client
                    .build();
            Wallet wallet3 = Wallet.builder()
                    .id(UUID.randomUUID().toString())
                    .balance(9800.19)
                    .createdAt(new Date())
                    .currency("POUND")
                    .client(client1) // Association du portefeuille au client
                    .build();

            walletRepository.save(wallet1);
            walletRepository.save(wallet2);
            walletRepository.save(wallet3);
        };
    }
}
