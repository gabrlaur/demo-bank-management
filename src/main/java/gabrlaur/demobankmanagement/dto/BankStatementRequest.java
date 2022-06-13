package gabrlaur.demobankmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BankStatementRequest {
    private Long beneficiaryId;
    private String comment;
    private BigDecimal amount;
    private String currency;
}
