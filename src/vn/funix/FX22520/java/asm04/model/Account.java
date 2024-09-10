package vn.funix.FX22520.java.asm04.model;

import vn.funix.FX22520.java.asm04.Utils;
import vn.funix.FX22520.java.asm04.dao.AccountDao;
import vn.funix.FX22520.java.asm04.dao.TransactionDao;
import vn.funix.FX22520.java.asm04.enums.TransactionType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static vn.funix.FX22520.java.asm04.model.SavingsAccount.*;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1;
    private String customerId;
    private String accountNumber;
    private double balance;
    private String accountType;

    public Account(String customerId, String accountNumber, double balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void input(Scanner scanner) {
        //thêm tk mới vào danh sách
    }

    public void createTransaction(double amount, String time, boolean status, TransactionType type) {

    }

    public void displayTransactionsList() {
        //lấy ra list transaction từ hàm getTransaction rồi in?
        getTransactionHistories();//tạm thời vậy đã
    }

    public void getCustomer() {

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

    public void addTransaction(Transaction transaction) {
        AccountDao.read();
    }

    public List<Transaction> getTransactionHistories() {
        return TransactionDao.read();
    }

    @Override
    public String toString() {
        return String.format("CustomerID: %s | %s | AccountNumber: %s | Balance: %.2f", customerId, "SAVINGS", accountNumber, balance);
    }

    public String getAccountType() {
        if (getBalance() >= 10_000_000) {
            return "premium account";
        }
        return "basic account";
    }

    public boolean isAccepted(double amount) {
        //kiểm tra điều kiện xem có thỏa mãn ko, gồm cả số dư hiện có
        return (getAccountType().equals("basic account") ? amount < SAVINGS_ACCOUNT_MAX_WITHDRAW : amount > 50000)
                && (amount % 10000 == 0)
                && (amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW)
                && (getBalance() - amount >= 50_000);
    }

    public boolean withdraw(Account withdrawAccount, double amount) {
        double newBalance;
        if (isAccepted(amount)) {
            newBalance = withdrawAccount.getBalance() - amount;
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    public boolean transfer(Account transferAccount, double amount) {
        double newBalance;
        if (isAccepted(amount)) {
            newBalance = transferAccount.getBalance() - amount;
            setBalance(newBalance);
            return true;
        }
        return false;
    }

    public boolean receive(Account receiveAccount, double amount) {
        double newBalance;
        newBalance = receiveAccount.getBalance() + amount;
        setBalance(newBalance);
        return true;
    }


    public void log(double amount, TransactionType type, Account transferAccount, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%-40s%n", Utils.getTitleSavings());
        System.out.printf("%-20s: %20s%n", "Ngày giao dịch", Utils.getDateTime());
        System.out.printf("%-20s: %20s%n", "ATM ID", "DIGITAL-BANK-2024");
        System.out.printf("%-20s: %20s%n", "ACCOUNT NUMBER", getAccountNumber());
        System.out.printf("%-20s: %20s%n", "SỐ TIỀN GD", Utils.formatBalance(amount));
        System.out.printf("%-20s: %20s%n", "SỐ DƯ CÒN LẠI", Utils.formatBalance(transferAccount.getBalance()));
        System.out.printf("%-20s: %20s%n", "PHÍ + VAT", Utils.formatBalance(amount * TRANSACTION_FEE));
        System.out.printf("%-20s: %20s%n", "Loại giao dịch", type);
        System.out.printf("%-20s: %20s%n", "Tài khoản nhận", receiveAccount.getAccountNumber());
        System.out.println(Utils.getDivider());
    }


    public void log(double amount, TransactionType type, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitleSavings());
        System.out.printf("Ngày giao dịch: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-2024");
        System.out.printf("ACCOUNT NUMBER: %30s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN RÚT: %30s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ CÒN LẠI: %30s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %30s%n", Utils.formatBalance(amount * TRANSACTION_FEE));
        System.out.printf("Loại giao dịch: %30s%n", type);
        System.out.println(Utils.getDivider());
    }
}