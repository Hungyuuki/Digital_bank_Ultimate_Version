package vn.funix.FX22520.java.asm03.models;

import java.text.DecimalFormat;
import java.util.Date;

public class Utils implements IReport {
    @Override
    public void log(double amount) {

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
