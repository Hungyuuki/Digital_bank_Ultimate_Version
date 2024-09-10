package vn.funix.FX22520.java.asm04.enums;

public enum TransactionType {
    DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW"), TRANSFER("TRANSFER");

    private String value;

    TransactionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static TransactionType getTransactionType(TransactionType value) {
        TransactionType[] values = values();
        for (TransactionType transactionType : values) {
            if (transactionType ==value)
                return transactionType;
        }
        return null;
    }
}
