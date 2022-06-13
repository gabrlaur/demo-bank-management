package gabrlaur.demobankmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementDetails {
    private String account;
    private String comment;
    private BigDecimal amount;
    private String currency;
    private String date;
}
