package gabrlaur.demobankmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountTotal {
    private String account;
    private BigDecimal total;
}
