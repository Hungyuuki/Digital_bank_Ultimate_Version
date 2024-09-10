package vn.funix.FX22520.java.asm04.dao;
import vn.funix.FX22520.java.asm04.model.Transaction;
import vn.funix.FX22520.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

import static vn.funix.FX22520.java.asm04.staticValue.Message.FILE_TRANSACTION_DAT;

public class TransactionDao {

    public static void save(List<Transaction> transactionList) throws IOException {
        BinaryFileService.writeFile(FILE_TRANSACTION_DAT, transactionList);
    }
    public static List<Transaction> read() {
        return BinaryFileService.readFile(FILE_TRANSACTION_DAT);
    }
}
