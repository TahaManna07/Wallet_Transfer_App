package ma.enset.transfertservice;

import lombok.AllArgsConstructor;
import ma.enset.transfertservice.clients.WalletRestClient;
import ma.enset.transfertservice.entities.Transfer;
import ma.enset.transfertservice.enums.TransferStatus;
import ma.enset.transfertservice.model.Wallet;
import ma.enset.transfertservice.repositories.TransferRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class TransfertServiceApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TransfertServiceApplication.class, args);
    }


    }



