package gabrlaur.demobankmanagement.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import gabrlaur.demobankmanagement.dto.AccountTotal;
import gabrlaur.demobankmanagement.dto.BankStatementDetails;
import gabrlaur.demobankmanagement.entity.BankStatement;
import lombok.SneakyThrows;

import java.util.List;

public class csvUtils {
    @SneakyThrows
    public static String getBankStatementCsv(Iterable<BankStatement> bankStatements) {
        CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        CsvSchema schema = mapper.schemaFor(BankStatement.class);
        return mapper.writer(schema.withUseHeader(true)).writeValueAsString(bankStatements);
    }

    @SneakyThrows
    public static List<BankStatementDetails> readBankStatementFromCSV(String csv) {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<BankStatementDetails> readValues = mapper.readerFor(BankStatementDetails.class).with(schema).readValues(csv);
        return readValues.readAll();
    }

    @SneakyThrows
    public static String getAccountTotalCsv(AccountTotal accountTotal) {
        CsvMapper mapper = new CsvMapper();
        mapper.findAndRegisterModules();
        CsvSchema schema = mapper.schemaFor(AccountTotal.class);
        return mapper.writer(schema.withUseHeader(true)).writeValueAsString(accountTotal);
    }
}
