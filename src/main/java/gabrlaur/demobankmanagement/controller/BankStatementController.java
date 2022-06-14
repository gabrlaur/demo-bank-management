package gabrlaur.demobankmanagement.controller;

import gabrlaur.demobankmanagement.dto.BankStatementRequest;
import gabrlaur.demobankmanagement.entity.BankStatement;
import gabrlaur.demobankmanagement.service.BankStatementService;
import gabrlaur.demobankmanagement.utils.csvUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/statement")
@AllArgsConstructor
public class BankStatementController {
    private final BankStatementService bankStatementService;

    @GetMapping("/{id}")
    public BankStatement getBankStatementById(@PathVariable Long id) {
        return this.bankStatementService.getBankStatementById(id);
    }

    @GetMapping
    public Iterable<BankStatement> getAllBankStatements() {
        return this.bankStatementService.getAllBankStatements();
    }

    @PostMapping
    public BankStatement createBankStatement(@RequestBody BankStatementRequest bankStatement) {
        return this.bankStatementService.createBankStatement(bankStatement);
    }

    @PutMapping("/{id}")
    public BankStatement updateBankStatement(@PathVariable Long id, @RequestBody BankStatementRequest bankStatementRequest) {
        return this.bankStatementService.updateBankStatement(id, bankStatementRequest);
    }

    @DeleteMapping("{id}")
    public void deleteBankStatement(@PathVariable Long id) {
        this.bankStatementService.deleteBankStatement(id);
    }

    @PostMapping("/upload")
    @Transactional
    public Iterable<BankStatement> uploadBankStatements(@RequestBody String bankStatementCsv) {
        return this.bankStatementService.uploadBankStatementCSV(csvUtils.readBankStatementFromCSV(bankStatementCsv));
    }

    @GetMapping(value = "/filter", produces = "text/csv")
    public String getBankStatements(@RequestParam(required = false) Long id,
                                    @RequestParam(required = false) String startDateTime,
                                    @RequestParam(required = false) String endDateTime) {
        return csvUtils.getBankStatementCsv(this.bankStatementService.getBankStatementsByBeneficiaryIdAndDates(id, startDateTime, endDateTime));
    }

    @GetMapping(value = "/total", produces = "text/csv")
    public String getAccountTotal(@RequestParam Long id,
                                  @RequestParam(required = false) String startDateTime,
                                  @RequestParam(required = false) String endDateTime) {
        return csvUtils.getAccountTotalCsv(this.bankStatementService.getAccountTotal(id, startDateTime, endDateTime));
    }
}