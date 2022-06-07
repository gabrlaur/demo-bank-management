package gabrlaur.demobankmanagement.repository;

import gabrlaur.demobankmanagement.entity.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankStatementRepository extends JpaRepository<BankStatement, Long> {
}
