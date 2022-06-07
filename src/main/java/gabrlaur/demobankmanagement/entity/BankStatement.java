package gabrlaur.demobankmanagement.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_statements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiary_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Beneficiary beneficiary;

    @Column(name = "comment")
    private String comment;

    @Column(name = "amount", nullable = false)
    @NotNull
    private BigDecimal amount;

    @Column(name = "currency", nullable = false)
    @NotNull
    private String currency;
}
