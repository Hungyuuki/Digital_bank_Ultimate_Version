package vn.funix.FX22520.java.asm04;

import vn.funix.FX22520.java.asm04.model.Account;
import vn.funix.FX22520.java.asm04.enums.TransactionType;

public interface IReport {
//    void log(double amount);
    void log(double amount, TransactionType type, Account receiveAccount);
    void log(double amount, TransactionType type, Account transferAccount, Account receiveAccount);
//thêm trường type để hiển thị biên lai giao dịch theo từng trường hợp rút tiền/chuyển tiền.
}
