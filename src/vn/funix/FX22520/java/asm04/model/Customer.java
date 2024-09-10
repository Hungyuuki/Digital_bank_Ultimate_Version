package vn.funix.FX22520.java.asm04.model;
import vn.funix.FX22520.java.asm04.dao.TransactionDao;
import vn.funix.FX22520.java.asm04.enums.TransactionType;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

import java.util.List;
import java.util.stream.Collectors;

import static vn.funix.FX22520.java.asm04.staticValue.Message.accountList;
import static vn.funix.FX22520.java.asm04.staticValue.Message.transactionList;

public class Customer extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Customer(String customerId, String name) {
        super(name, customerId);
    }

    public Customer(List<String> value) {
        this(value.get(0).trim(), value.get(1).trim());
    }

    public List<Account> getAccounts() {
        return accountList;
    }

    public List<Account> getAccounts(String customerId) {
        return accountList.stream().filter(account -> account.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public boolean isPremiumAccount() {
        for (Account account : getAccounts()) {
            if (account.isPremiumAccount()) {
                return true;
            }
        }
        return false;
    }

//    Phương thức có tham số là newAccount: Account, dùng để thêm tài khoản mới cho khách hàng.
//    Tài khoản được thêm khi và chỉ khi số tài khoản chưa từng tồn tại trước đó.
    public boolean addAccount(Account newAccount) {
        if (findAccount(newAccount.getAccountNumber()) == null) {
            getAccounts().add(newAccount);
            return true;
        }
        return false;
    }


    public Account findAccount(String accountNumber) {
        for (int i = 0; i < accountList.size(); i++) {
            Account checkedAccount = getAccounts().get(i);
            if (checkedAccount.getAccountNumber().equals(accountNumber)) {//Nếu tìm thấy
                return checkedAccount; //thì trả về account có accountNumber đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    //    dùng để tính toán số dư của khách hàng là tổng số dư của tất cả tài khoản mà khách hàng có.
    public double getBalance() {
        double sumOfBalance = 0d;
        for (int i = 0; i < getAccounts().size(); i++) {
            sumOfBalance += getAccounts().get(i).getBalance();
        }
        return sumOfBalance;
    }


    public void displayInformation() {
        for (int i = 0; i < getAccounts().size(); i++) {
            System.out.printf("%15s | %20s | %10s | %10s", (i + 1) + ".", getAccounts().get(i).getAccountNumber(), getAccounts().get(i).isPremiumAccount() ? "Premium" : "" + "      ", "    " + getAccounts().get(i).getBalance() + "\n");
            //generate account cell
        }
    }

    public boolean withdraw(String accountNumber, double amount, TransactionType type) throws IOException {
        boolean isCheck = false;
        Account withdrawAccount = findAccount(accountNumber);
        Transaction transaction;
            if (withdrawAccount.isAccepted(amount)) {
                // Nếu thỏa điều kiện rút tiền thì thực hiện việc rút tiền
                withdrawAccount.withdraw(withdrawAccount, amount);
                // Ghi log cho giao dịch này
                withdrawAccount.log(amount, type, withdrawAccount);
                // Đánh dấu giao dịch thành công
                isCheck = true;
            } else {
                System.out.println("Số tiền không được chấp nhận để rút.");
            }
        // Lưu giao dịch vào account
        transaction = new Transaction(accountNumber, -amount, isCheck, type);
        withdrawAccount.addTransaction(transaction);
        TransactionDao.save(transactionList);
        return isCheck;
    }

    public boolean transfer(String transferAccountNumber, String receiveAccountNumber, double amount, TransactionType type) throws IOException {
        boolean isCheck = false;
        Account transferAccount = findAccount(transferAccountNumber);
        Account receiveAccount = findAccount(receiveAccountNumber);
        Transaction transferTransaction;
        Transaction receiveTransaction;
        if (transferAccount.isAccepted(amount)) {
            // Thực hiện việc rút tiền, bên gửi thì trừ đi và bên nhận thì được cộng vào
            transferAccount.transfer(transferAccount, amount);
            receiveAccount.receive(receiveAccount, amount);

            // Ghi log cho giao dịch này
            transferAccount.log(amount, type, transferAccount, receiveAccount);
            // Đánh dấu giao dịch thành công
            isCheck = true;
        } else {
            System.out.println("Số tiền không được chấp nhận để rút.");
        }
        // Lưu giao dịch vào account
        transferTransaction = new Transaction(transferAccountNumber, amount, isCheck, type);
        receiveTransaction = new Transaction(receiveAccountNumber, amount, isCheck, type);
        transferAccount.addTransaction(transferTransaction);
        receiveAccount.addTransaction(receiveTransaction);
        TransactionDao.save(transactionList);
        return isCheck;
    }
}
