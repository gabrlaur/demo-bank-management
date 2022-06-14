package gabrlaur.demobankmanagement.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"account", "comment", "amount", "currency", "date"})
public class BankStatementDetails {
    private String account;
    private String comment;
    private BigDecimal amount;
    private String currency;
    private String date;
}