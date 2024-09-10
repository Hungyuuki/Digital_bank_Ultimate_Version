package vn.funix.FX22520.java.asm04.model;

import vn.funix.FX22520.java.asm04.Utils;
import vn.funix.FX22520.java.asm04.enums.TransactionType;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import static vn.funix.FX22520.java.asm04.enums.TransactionType.getTransactionType;
import static vn.funix.FX22520.java.asm04.enums.TransactionType.values;

public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String transactionId;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    //Dùng Serializable
    private TransactionType type;

    public Transaction(String accountNumber, double amount, boolean status, TransactionType type) {
        this.transactionId = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = Utils.getDateTime();
        this.status = status;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getTransactionId() + "  |  " + getAccountNumber() + "  |  " + getAmount() + "  |  " + getTime() + "  |  " + TransactionType.getTransactionType(getType());
    }
}
