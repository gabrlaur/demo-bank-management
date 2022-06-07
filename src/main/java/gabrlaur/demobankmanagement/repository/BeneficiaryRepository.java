package gabrlaur.demobankmanagement.repository;

import gabrlaur.demobankmanagement.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}