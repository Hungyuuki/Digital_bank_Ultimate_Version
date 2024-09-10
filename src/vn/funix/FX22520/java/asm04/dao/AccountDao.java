package vn.funix.FX22520.java.asm04.dao;

import vn.funix.FX22520.java.asm04.model.Account;
import vn.funix.FX22520.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static vn.funix.FX22520.java.asm04.staticValue.Message.FILE_ACCOUNT_DAT;


public class AccountDao {
    public static void save(List<Account> accountList) throws IOException {//thực chất là ghi lại vào file mới
        BinaryFileService.writeFile(FILE_ACCOUNT_DAT, accountList);
    }
    public static List<Account> read() {
        return BinaryFileService.readFile(FILE_ACCOUNT_DAT);
    }

    public static void update(Account editAccount) {//update account
        List<Account> accounts = read();
        boolean hasExits = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
//        kiểm tra tk có tồn tại chưa, trả ra boolean
        //anyMatch là 1 phương thức của Predicates, trả ra boolean
        //kiểm tra accountNumber mới truyền vào có bằng account number nào trong list accounts hay không, bằng thì true, không thì false.
//        Thât ra đoạn này để phục vụ cho chức năng số 2
        List<Account> updateAccounts;
        if(!hasExits) {
            updateAccounts = new ArrayList<>(accounts);
            updateAccounts.add(editAccount);
        } else {
            updateAccounts = new ArrayList<>();
            for (Account account :
                    accounts) {
                if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
                    updateAccounts.add(editAccount);
                } else updateAccounts.add(account);
            }
        }
        try {
            save(updateAccounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
