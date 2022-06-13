package gabrlaur.demobankmanagement.service;

import gabrlaur.demobankmanagement.dto.AccountTotal;
import gabrlaur.demobankmanagement.dto.BankStatementDetails;
import gabrlaur.demobankmanagement.dto.BankStatementRequest;
import gabrlaur.demobankmanagement.entity.BankStatement;
import gabrlaur.demobankmanagement.entity.Beneficiary;
import gabrlaur.demobankmanagement.repository.BankStatementRepository;
import gabrlaur.demobankmanagement.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {
    private final BankStatementRepository bankStatementRepository;

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    public BankStatement getBankStatementById(Long id) {
        return this.bankStatementRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank statement not found")
        );
    }

    @Override
    public Iterable<BankStatement> getAllBankStatements() {
        return this.bankStatementRepository.findAll();
    }

    @Override
    public Iterable<BankStatement> getBankStatementsByBeneficiaryIdAndDates(Long id, String startDateTime, String endDateTime) {
        if (id != null && startDateTime != null && endDateTime != null) {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setId(id);
            return this.bankStatementRepository.findAllByBeneficiaryAndDateTimeBetween(beneficiary,
                    LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
        } else if (id != null && startDateTime != null) {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setId(id);
            return this.bankStatementRepository.findAllByBeneficiaryAndDateTimeGreaterThanEqual(beneficiary,
                    LocalDateTime.parse(startDateTime));
        } else if (id != null && endDateTime != null) {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setId(id);
            return this.bankStatementRepository.findAllByBeneficiaryAndDateTimeLessThanEqual(beneficiary,
                    LocalDateTime.parse(endDateTime));
        } else if (id != null) {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.setId(id);
            return this.bankStatementRepository.findAllByBeneficiary(beneficiary);
        } else if (startDateTime != null && endDateTime != null) {
            return this.bankStatementRepository.findAllByDateTimeBetween(LocalDateTime.parse(startDateTime), LocalDateTime.parse(endDateTime));
        } else if (startDateTime != null) {
            return this.bankStatementRepository.findAllByDateTimeGreaterThanEqual(LocalDateTime.parse(startDateTime));
        } else if (endDateTime != null) {
            return this.bankStatementRepository.findAllByDateTimeLessThanEqual(LocalDateTime.parse(endDateTime));
        } else return null;
    }

    @Override
    public BankStatement createBankStatement(BankStatementRequest bankStatement) {
        BankStatement newBankStatement = new BankStatement();
        newBankStatement.setBeneficiary(this.beneficiaryRepository.findById(bankStatement.getBeneficiaryId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found")));
        newBankStatement.setDateTime(LocalDateTime.now());
        newBankStatement.setCurrency(bankStatement.getCurrency());
        newBankStatement.setAmount(bankStatement.getAmount());
        newBankStatement.setComment(bankStatement.getComment());
        return this.bankStatementRepository.save(newBankStatement);
    }

    @Override
    public BankStatement updateBankStatement(Long id, BankStatementRequest newBankStatement) {
        BankStatement bankStatement = this.bankStatementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank statement not found"));

        bankStatement.setAmount(newBankStatement.getAmount());
        bankStatement.setCurrency(newBankStatement.getCurrency());
        bankStatement.setComment(newBankStatement.getComment());
        bankStatement.setDateTime(LocalDateTime.now());
        bankStatement.setBeneficiary(this.beneficiaryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found")
        ));

        return bankStatement;
    }

    @Override
    public void deleteBankStatement(Long id) {
        if (bankStatementRepository.existsById(id)) {
            this.bankStatementRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank statement not found");
    }

    @Override
    public Iterable<BankStatement> uploadBankStatementCSV(Iterable<BankStatementDetails> bankStatementDetails) {
        List<BankStatement> newBankStatements = new ArrayList<>();
        bankStatementDetails.forEach(bankStatement -> {
            BankStatement newBankStatement = new BankStatement();
            newBankStatement.setAmount(bankStatement.getAmount());
            newBankStatement.setCurrency(bankStatement.getCurrency());
            newBankStatement.setComment(bankStatement.getComment());
            newBankStatement.setDateTime(LocalDateTime.parse(bankStatement.getDate()));
            newBankStatement.setBeneficiary(this.beneficiaryRepository.findByAccountNumber(bankStatement.getAccount()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found with account: " + bankStatement.getAccount())
            ));
            newBankStatements.add(newBankStatement);
        });
        return this.bankStatementRepository.saveAll(newBankStatements);
    }

    @Override
    public AccountTotal getAccountTotal(Long id, String startDateTime, String endDateTime) {
        List<BankStatement> bankStatements = (ArrayList<BankStatement>) getBankStatementsByBeneficiaryIdAndDates(id, startDateTime, endDateTime);
        BigDecimal total = BigDecimal.valueOf(0);
        for (BankStatement bankStatement : bankStatements) {
            total = total.add(bankStatement.getAmount());
        }
        return new AccountTotal(this.beneficiaryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Beneficiary not found")).getAccountNumber(), total);
    }
}
