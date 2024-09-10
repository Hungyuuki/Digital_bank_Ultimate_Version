package vn.funix.FX22520.java.asm03.models;

import java.util.UUID;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;

    public Transaction(String accountNumber, double amount, boolean status) {
        this.transactionId = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = Utils.getDateTime();
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }
}
