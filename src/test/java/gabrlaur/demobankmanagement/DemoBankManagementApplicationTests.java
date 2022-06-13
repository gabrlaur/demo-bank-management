package gabrlaur.demobankmanagement;

import gabrlaur.demobankmanagement.controller.BankStatementController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoBankManagementApplicationTests {

    @Autowired
    private BankStatementController bankStatementController;

    @Test
    void verifyGetBankStatementsWithAllParams() {
        assertThat(bankStatementController.getBankStatements(1L, "2015-08-04T10:11:30", "2025-08-04T10:11:30"))
                .contains("LT123123123,\"this is a first transaction\",12,USD,2022-06-08T02:18:04");
    }

    @Test
    void verifyGetBankStatementsWithIdOnly() {
        assertThat(bankStatementController.getBankStatements(1L, null, null))
                .contains("LT123123123,\"this is a first transaction\",12,USD,2022-06-08T02:18:04");
    }

    @Test
    void verifyGetBankStatementIdNoMatches() {
        assertThat(bankStatementController.getBankStatements(99L, null, null))
                .isEqualTo("account,comment,amount,currency,date\n");
    }

    @Test
    void verifyGetBankStatementDateNoMatches() {
        assertThat(bankStatementController.getBankStatements(1L, "2070-08-04T10:11:30", "2071-08-04T10:11:30"))
                .isEqualTo("account,comment,amount,currency,date\n");
    }

    @Test
    void verifyGetAccountTotalWithAllParams() {
        assertThat(bankStatementController.getAccountTotal(1L, "2022-06-08T02:18:04", "2022-06-08T05:18:04"))
                .contains("LT123123123,12");
    }

    @Test
    void verifyGetAccountTotalNoMatches() {
        assertThat(bankStatementController.getAccountTotal(1L, "2029-06-08T02:18:04", "2029-06-08T05:18:04"))
                .isEqualTo("account,total\n");
    }

    @Test
    void verifyBankDetailsUpload() {
        bankStatementController.uploadBankStatements("account,comment,amount,currency,date\n" +
                "LT123123123,test transaction,-500,USD,2022-06-04T10:11:30");
        assertThat(bankStatementController.getBankStatements(1L, "2022-06-04T09:11:30", "2022-06-04T11:11:30"))
                .contains("LT123123123,\"test transaction\",-500,USD,2022-06-04T10:11:30");
    }
}
