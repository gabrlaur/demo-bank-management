package gabrlaur.demobankmanagement.service;

import gabrlaur.demobankmanagement.dto.AccountTotal;
import gabrlaur.demobankmanagement.dto.BankStatementDetails;
import gabrlaur.demobankmanagement.dto.BankStatementRequest;
import gabrlaur.demobankmanagement.entity.BankStatement;

public interface BankStatementService {
    BankStatement getBankStatementById(Long id);

    Iterable<BankStatement> getAllBankStatements();

    Iterable<BankStatementDetails> getBankStatementsByBeneficiaryIdAndDates(Long id, String startDateTime, String endDateTime);

    BankStatement createBankStatement(BankStatementRequest bankStatement);

    BankStatement updateBankStatement(Long id, BankStatementRequest bankStatement);

    void deleteBankStatement(Long id);

    Iterable<BankStatement> uploadBankStatementCSV(Iterable<BankStatementDetails> bankStatement);

    AccountTotal getAccountTotal(Long id, String startDateTime, String endDateTime);
}