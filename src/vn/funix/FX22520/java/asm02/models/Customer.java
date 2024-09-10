package vn.funix.FX22520.java.asm02.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> accounts;

    public Customer(String name, String customerId) {
        super(name, customerId);
        this.accounts = new ArrayList<>();
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public boolean isPremiumAccount() {
        for (Account account : accounts) {
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
            this.accounts.add(newAccount);
            return true;
        }
        return false;
    }


    public Account findAccount(String accountNumber) {
        for (int i = 0; i < this.accounts.size(); i++) {
            Account checkedAccount = this.accounts.get(i);
            if (checkedAccount.getAccountNumber().equals(accountNumber)) {//Nếu tìm thấy
                return checkedAccount; //thì trả về account đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    //    dùng để tính toán số dư của khách hàng là tổng số dư của tất cả tài khoản mà khách hàng có.
    public double getBalance() {
        double sumOfBalance = 0d;
        for (int i = 0; i < accounts.size(); i++) {
            sumOfBalance += accounts.get(i).getBalance();
        }
        return sumOfBalance;
    }

    public void displayInformation() {
        for (int i = 0; i < getAccounts().size(); i++) {
            System.out.printf("%15s | %20s | %20s | %10s", (i + 1) + ".", getAccounts().get(i).getAccountNumber(), getAccounts().get(i).isPremiumAccount() ? "Premium" : "" + "      ", "    " + getAccounts().get(i).getBalance());
            //generate account cell
            System.out.println();
        }
    }
}
