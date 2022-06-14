package gabrlaur.demobankmanagement.controller;

import gabrlaur.demobankmanagement.entity.Beneficiary;
import gabrlaur.demobankmanagement.service.BeneficiaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/beneficiary")
@AllArgsConstructor
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;

    @GetMapping("/{id}")
    public Beneficiary getBeneficiaryById(@PathVariable Long id) {
        return this.beneficiaryService.getBeneficiaryDetails(id);
    }

    @GetMapping
    public Iterable<Beneficiary> getAllBeneficiaries() {
        return this.beneficiaryService.getAllBeneficiaries();
    }

    @PostMapping
    public Beneficiary createBeneficiary(@Valid @RequestBody Beneficiary beneficiary) {
        return this.beneficiaryService.createBeneficiary(beneficiary);
    }

    @PutMapping("/{id}")
    public Beneficiary updateBeneficiary(@PathVariable Long id, @Valid @RequestBody Beneficiary beneficiary) {
        return this.beneficiaryService.updateBeneficiary(id, beneficiary);
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id) {
        this.beneficiaryService.deleteBeneficiary(id);
    }
}