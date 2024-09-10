package vn.funix.FX22520.java.asm02.models;


public class Account {
    private String accountNumber;
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isPremiumAccount() {
        return this.balance >= 10_000_000;
    }

    //    xuất thông tin tài khoản gồm mã tài khoản và số dư:
    @Override
    public String toString() {
        return this.accountNumber + "|                                " + this.balance;
    }
}
