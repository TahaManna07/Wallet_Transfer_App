package ma.enset.transfertservice.repositories;

import ma.enset.transfertservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
