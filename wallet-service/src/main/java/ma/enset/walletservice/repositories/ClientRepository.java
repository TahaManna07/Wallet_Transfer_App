package ma.enset.walletservice.repositories;

import ma.enset.walletservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
