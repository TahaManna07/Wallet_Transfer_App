package ma.enset.transfertservice.clients;

import ma.enset.transfertservice.dtos.WalletUpdateRequest;
import ma.enset.transfertservice.model.Wallet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(name = "WALLET-SERVICE")
public interface WalletRestClient {

    @GetMapping("/wallets/{id}")
    Wallet getWalletById(@PathVariable String id);

    @PutMapping("/wallets/update")
    Wallet updateWalletBalance(@RequestBody WalletUpdateRequest request);


}

