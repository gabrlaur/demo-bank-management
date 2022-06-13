package gabrlaur.demobankmanagement.repository;

import gabrlaur.demobankmanagement.entity.BankStatement;
import gabrlaur.demobankmanagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {
    Iterable<BankStatement> findAllByBeneficiaryAndDateTimeBetween(Beneficiary beneficiary,
                                                                   LocalDateTime startDateTime,
                                                                   LocalDateTime endDateTime);
    Iterable<BankStatement> findAllByBeneficiaryAndDateTimeLessThanEqual(Beneficiary beneficiary, LocalDateTime endDateTime);

    Iterable<BankStatement> findAllByBeneficiaryAndDateTimeGreaterThanEqual(Beneficiary beneficiary, LocalDateTime startDateTime);

    Iterable<BankStatement> findAllByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    Iterable<BankStatement> findAllByDateTimeLessThanEqual(LocalDateTime endDateTime);

    Iterable<BankStatement> findAllByDateTimeGreaterThanEqual(LocalDateTime startDateTime);

    Iterable<BankStatement> findAllByBeneficiary(Beneficiary beneficiary);

}
