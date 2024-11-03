package ma.enset.transfertservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Wallet {
    private String id;
    private Double balance;
    private Date createdAt;
    private String currency;
}
