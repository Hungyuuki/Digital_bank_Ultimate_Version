package vn.funix.FX22520.java.asm04;

import vn.funix.FX22520.java.asm04.model.Account;
import vn.funix.FX22520.java.asm04.enums.TransactionType;

import java.text.DecimalFormat;
import java.util.Date;

import static vn.funix.FX22520.java.asm04.model.SavingsAccount.TRANSACTION_FEE;

public class Utils implements IReport {
    /**
     * Dùng cho gd withdraw
     * @param amount
     * @param type
     * @param receiveAccount
     */

    @Override
    public void log(double amount, TransactionType type, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%-50s%n", Utils.getTitleSavings());
        System.out.printf("%-30s : %20s%n", "Ngày giao dịch", Utils.getDateTime());
        System.out.printf("%-30s : %20s%n", "ATM ID", "DIGITAL-BANK-2024");
        System.out.printf("%-30s : %20s%n", "ACCOUNT NUMBER", receiveAccount.getAccountNumber());
        System.out.printf("%-30s : %20s%n", "SỐ TIỀN GD", Utils.formatBalance(amount));
        System.out.printf("%-30s : %20s%n", "SỐ DƯ CÒN LẠI", Utils.formatBalance(receiveAccount.getBalance()));
        System.out.printf("%-30s : %20s%n", "PHÍ + VAT", Utils.formatBalance(amount * TRANSACTION_FEE));
        System.out.printf("%-30s : %20s%n", "Loại giao dịch", type);
        System.out.printf("%-30s : %20s%n", "Tài khoản nhận là", receiveAccount.getAccountNumber());
        System.out.println(Utils.getDivider());
    }

    /**
     * Dùng cho gd transfer
     * @param amount
     * @param type
     * @param transferAccount
     * @param receiveAccount
     */
    @Override
    public void log(double amount, TransactionType type, Account transferAccount, Account receiveAccount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitleSavings());
        System.out.printf("Ngày giao dịch: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-2024");
        System.out.printf("ACCOUNT NUMBER: %30s%n", transferAccount.getAccountNumber());
        System.out.printf("SỐ TIỀN GD: %30s%n", Utils.formatBalance(amount));
        System.out.printf("SỐ DƯ CÒN LẠI: %30s%n", Utils.formatBalance(transferAccount.getBalance()));
        System.out.printf("PHÍ + VAT: %30s%n", Utils.formatBalance(amount*TRANSACTION_FEE));
        System.out.printf("Loại giao dịch: %30s%n", type);
        System.out.printf("Tài khoản nhận là: %30s%n", receiveAccount.getAccountNumber());
        System.out.println(Utils.getDivider());
    }

    public static String getTitleSavings() {
        return "BIÊN LAI GIAO DỊCH SAVINGS";
    }

    public static String getTitleLoan() {
        return "BIÊN LAI GIAO DỊCH LOAN";
    }

    public static String getDivider() {
        return "-----------------------------------------";
    }

    public static String getDateTime() {
        long miliSeconds = System.currentTimeMillis();
        Date date = new Date(miliSeconds);
        return date.toString();
    }

    public static String formatBalance(double balance) {
        DecimalFormat format = new DecimalFormat("#,###đ");
        return format.format(balance);
    }

}
