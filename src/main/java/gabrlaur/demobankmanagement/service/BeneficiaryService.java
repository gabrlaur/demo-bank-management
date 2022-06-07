package gabrlaur.demobankmanagement.service;

import gabrlaur.demobankmanagement.entity.Beneficiary;

public interface BeneficiaryService {
    Beneficiary getBeneficiaryDetails(Long id);

    Iterable<Beneficiary> getAllBeneficiaries();

    Beneficiary createBeneficiary(Beneficiary newBeneficiary);

    Beneficiary updateBeneficiary(Long id, Beneficiary newBeneficiary);

    void deleteBeneficiary(Long id);
}
