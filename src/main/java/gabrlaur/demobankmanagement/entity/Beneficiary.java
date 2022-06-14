package gabrlaur.demobankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "beneficiaries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
    @GenericGenerator(name = "seq", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    private String lastName;

    @Column(name = "account_number", nullable = false)
    @NotNull
    private String accountNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "beneficiary", cascade = CascadeType.REMOVE)
    private Set<BankStatement> bankStatementSet;
}