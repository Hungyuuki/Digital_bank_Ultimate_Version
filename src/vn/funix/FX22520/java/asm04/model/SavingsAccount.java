package vn.funix.FX22520.java.asm04.model;

import vn.funix.FX22520.java.asm04.ITransfer;
import vn.funix.FX22520.java.asm04.enums.TransactionType;
import vn.funix.FX22520.java.asm04.IReport;
import vn.funix.FX22520.java.asm04.IWithDraw;
import vn.funix.FX22520.java.asm04.Utils;

public class SavingsAccount extends Account implements IReport, IWithDraw, ITransfer {
    public static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    public static final double SAVINGS_ACCOUNT_MIN_WITHDRAW = 50000;
    public static final double TRANSACTION_FEE = 0;

    public SavingsAccount(String customerId, String accountNumber, double balance) {
        super(customerId, accountNumber, balance);
    }


    @Override
    public void log(double amount, TransactionType type, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitleSavings());
        System.out.printf("Ngày giao dịch: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-2024");
        System.out.printf("ACCOUNT NUMBER: %30s%n", receiveAccount.getAccountNumber());
        System.out.printf("SỐ TIỀN GD: %30s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ CÒN LẠI: %30s%n", Utils.formatBalance(receiveAccount.getBalance()));
        System.out.printf("PHÍ + VAT: %30s%n", Utils.formatBalance(amount*TRANSACTION_FEE));
        System.out.printf("Loại giao dịch: %30s%n", type);
        System.out.printf("Tài khoản nhận là: %30s%n", receiveAccount.getAccountNumber());
        System.out.println(Utils.getDivider());
    }

    @Override
    public void log(double amount, TransactionType type, Account transferAccount, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitleSavings());
        System.out.printf("Ngày giao dịch: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-2024");
        System.out.printf("ACCOUNT NUMBER: %30s%n", getAccountNumber());
        System.out.printf("SỐ TIỀN GD: %30s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ CÒN LẠI: %30s%n", Utils.formatBalance(transferAccount.getBalance()));
        System.out.printf("PHÍ + VAT: %30s%n", Utils.formatBalance(amount*TRANSACTION_FEE));
        System.out.printf("Loại giao dịch: %30s%n", type);
        System.out.printf("Tài khoản nhận là: %30s%n", receiveAccount.getAccountNumber());
        System.out.println(Utils.getDivider());
    }


    public String getAccountType() {
        if (getBalance() >= 10_000_000) {
            return "premium account";
        }
        return "basic account";
    }

    //    Số tiền rút phải lớn hơn hoặc bằng 50.000đ
    //    Số tiền 1 lần rút không được quá 5.000.000đ đối với tài khoản thường, và không giới hạn đối với tài khoản Premium.
    //    Số dư còn lại sau khi rút phải lớn hơn hoặc bằng 50.000đ
    //    Số tiền rút phải là bội số của 10.000đ
    @Override
    public boolean isAccepted(double amount) {
        //kiểm tra điều kiện xem có thỏa mãn ko, gồm cả số dư hiện có
        return (getAccountType().equals("basic account") ? amount < SAVINGS_ACCOUNT_MAX_WITHDRAW : amount > 50000)
                && (amount % 10000 == 0)
                && (amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW)
                && (getBalance() - amount >= 50_000);
    }


    // ko tính phí, lấy balance hiện tại - withdraw amount,
    // rút tiền thành công thì trả về true, còn ko thì false
    @Override
    public boolean withdraw(double amount) {
        double newBalance = 0.0;
        if (isAccepted(amount)) {
            newBalance = getBalance() - amount;
            setBalance(newBalance);
            return true;
        }
        return false;
    }

//    public boolean transfer(Account transferAccount, double amount) {
//        double newBalance = 0.0;
//        if (isAccepted(amount)) {
//            newBalance = getBalance() - amount;
//            setBalance(newBalance);
//            return true;
//        }
//        return false;
//    }

    @Override
    public void transter(Account receiveAccount, double amount) {
        double newBalance = 0.0;
        if (isAccepted(amount)) {
            newBalance = getBalance() + amount;
            setBalance(newBalance);
        }
    }
}
