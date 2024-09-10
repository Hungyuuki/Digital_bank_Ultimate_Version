package vn.funix.FX22520.java.asm03.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private String accountType;
    private List<Transaction> accountTransactions;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountTransactions = new ArrayList<>();
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

    @Override
    public String toString() {
        return this.accountNumber + "|                                " + this.balance;
    }

    public void addTransaction(Transaction transaction) {
        accountTransactions.add(transaction);
    }
    public List<Transaction> getTransactionHistories() {
        return accountTransactions;
    }
}