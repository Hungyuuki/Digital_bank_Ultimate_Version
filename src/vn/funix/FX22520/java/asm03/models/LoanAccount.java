package vn.funix.FX22520.java.asm03.models;

public class LoanAccount extends Account implements IReport, IWithDraw {
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000d;
    private double transactionFee;

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public LoanAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitleLoan());
        System.out.printf("Ngày giao dịch: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-2024");
        System.out.printf("ACCOUNT NUMBER: %30s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN GD: %30s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ CÒN LẠI: %30s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHÍ + VAT: %30s%n", Utils.formatBalance(getTransactionFee()));
        System.out.println(Utils.getDivider());
    }

    public double getFee() {
        return isPremiumAccount() ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;
    }

    //    Hạn mức không được quá giới hạn 100.000.000đ
    //    Phí cho mỗi lần giao dịch là 0.05 trên số tiền giao dịch đối với tài khoản thường
    //    và 0.01 trên số tiền giao dịch đối với tài khoản Premium.
    //    Hạn mức còn lại sau khi rút tiền không được nhỏ hơn 50.000đ
    //    Gọi làm log để in ra biên lai theo từng loại tài khoản mỗi khi rút tiền thành công
    @Override
    public boolean isAccepted(double amount) { //hàm này được dùng lại trong hàm withdraw
        if ((amount < LOAN_ACCOUNT_MAX_BALANCE)
                && (amount % 10000 == 0)
                && (getBalance() - amount >= 50_000)) {
            return true;
        }
        return false;
    }
    //    Phí cho mỗi lần giao dịch là 0.05 trên số tiền giao dịch đối với tài khoản thường
    //    và 0.01 trên số tiền giao dịch đối với tài khoản Premium.
    @Override
    public boolean withdraw(double amount) {//tính phí
        if (isAccepted(amount)) {
            double newBalance = getBalance() - amount;
            double realFee = amount * getFee();
            setTransactionFee(realFee);
            setBalance(newBalance - realFee);
            return true;
        }
        return false;
    }
}
