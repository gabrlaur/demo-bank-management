package gabrlaur.demobankmanagement.service;

import gabrlaur.demobankmanagement.entity.Beneficiary;
import gabrlaur.demobankmanagement.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@AllArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {
    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary getBeneficiaryDetails(Long id) {
        return this.beneficiaryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found"));
    }

    @Override
    public Iterable<Beneficiary> getAllBeneficiaries() {
        return this.beneficiaryRepository.findAll();
    }

    @Override
    public Beneficiary createBeneficiary(Beneficiary newBeneficiary) {
        return this.beneficiaryRepository.save(newBeneficiary);
    }

    @Override
    public Beneficiary updateBeneficiary(Long id, Beneficiary newBeneficiary) {
        Beneficiary beneficiary = this.beneficiaryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found"));

        beneficiary.setFirstName(newBeneficiary.getFirstName());
        beneficiary.setLastName(newBeneficiary.getLastName());
        beneficiary.setAccountNumber(newBeneficiary.getAccountNumber());
        beneficiary.setBankStatementSet(newBeneficiary.getBankStatementSet());

        return beneficiary;
    }

    @Override
    public void deleteBeneficiary(Long id) {
        if (this.beneficiaryRepository.findById(id).isPresent()) {
            this.beneficiaryRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found");
    }
}
