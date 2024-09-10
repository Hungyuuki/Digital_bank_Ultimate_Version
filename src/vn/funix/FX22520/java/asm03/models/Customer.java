package vn.funix.FX22520.java.asm03.models;



import java.util.ArrayList;
import java.util.List;

public abstract class Customer extends User {
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

    public abstract void displayInformation();
}
