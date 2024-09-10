package vn.funix.FX22520.java.asm03;

import vn.funix.FX22520.java.asm03.models.*;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Asm03 {
    public static final String AUTHOR = "FX22520";
    public static final String VERSION = "@v3.0.0";
    public static final int EXIT_COMMAND_CODE = 0;
    public static final int EXIT_ERROR_CODE = -1;
    static Scanner sc = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "022094003697";
    private static final String CUSTOMER_NAME = "Phạm Mạnh Hùng";

    public static void main(String[] args) {
        initCustomer();
        initMainMenu();
    }

    private static void initMainMenu() {
        while (true) {
            //UI design
            System.out.printf("%s\n", "+--------------+----------------+--------------+");
            System.out.println("|   NGAN HANG SO   |   " + AUTHOR + "    |   " + VERSION + "  |");
            System.out.printf("%s\n", "+--------------+----------------+--------------+");
            System.out.printf("%15s" , "| 1. Thông tin khách hàng                      | \n");
            System.out.printf("%15s" , "| 2. Thêm tài khoản savings cho khách hàng     | \n");
            System.out.printf("%15s" , "| 3. Thêm tài khoản loan tín dụng              | \n");
            System.out.printf("%15s" , "| 4. Rút tiền                                  | \n");
            System.out.printf("%15s" , "| 5. Lịch sử giao dịch                         | \n");
            System.out.printf("%15s" , "| 0. Thoát                                     | \n");
            System.out.printf("%s\n", "+--------------+----------------+--------------+");
            System.out.println("Chức năng: ");
            try {
                whenProgramIsRunning();
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Nhập số không nhập chữ.");
                whenProgramIsRunning();
            }
        }
    }

    public static void initCustomer() {
        activeBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
    }

    public static void whenProgramIsRunning() {
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                System.out.println("Thông tin khách hàng");
                showCustomer();
                break;
            case 2:
                System.out.println("Thêm tài khoản savings ATM cho khách hàng");
                inputAccount(CUSTOMER_ID, 1);
                break;
            case 3:
                System.out.println("Thêm tài khoản loan tín dụng cho khách hàng");
                inputAccount(CUSTOMER_ID, 0);
                break;
            case 4:
                System.out.println("Rút tiền");
                System.out.println("Nhập số tài khoản cần rút:");
                String accountNumber = sc.nextLine();
                Account checkAccountNumber = activeBank.findAccount(accountNumber);
                if (checkAccountNumber != null) {
                    System.out.println("Nhập số tiền cần rút");
                    double withdrawAmount = Double.parseDouble(sc.nextLine());
                    if (activeBank.withdraw(CUSTOMER_ID, accountNumber, withdrawAmount)) {
                        System.out.println("Thanh cong");
                    } else {
                        System.out.println("Khong thanh cong");
                    }
                }
                break;
            case 5:
                System.out.println("Lịch sử giao dịch");
                System.out.println(Utils.getDivider());
                for (DigitalCustomer digitalCustomer : activeBank.getCustomer()) {
                    System.out.printf("%15s | %20s | %30s | %30s", digitalCustomer.getCustomerId(), digitalCustomer.getName(), digitalCustomer.isPremiumAccount() ? "Premium" : "" + "      ", digitalCustomer.getBalance());
                }
                System.out.println("\n");
                System.out.printf("%15s | %20s | %30s | %30s", "Account", "Amount", "Time", "Transaction ID");
                System.out.println("\n");
                for (DigitalCustomer digitalCustomer : activeBank.getCustomer()) {
                    for (Account account : digitalCustomer.getAccounts()) {
                        for (Transaction transaction : account.getTransactionHistories()) {
                            System.out.printf("%15s | %20s | %30s | %30s", "GD   " + transaction.getAccountNumber(), Utils.formatBalance(transaction.getAmount()), transaction.getTime(), transaction.getTransactionId());
                            System.out.println("\n");
                        }
                    }
                }
                break;
            case 0:
                System.out.println("Thoát");
                System.exit(EXIT_COMMAND_CODE);
                System.out.println("Exit!!!");
                sc.close();
        }
    }

    public static void showCustomer() {
        for (DigitalCustomer digitalCustomer : activeBank.getCustomer()) {
            System.out.printf("%15s | %20s | %20s | %10s", digitalCustomer.getCustomerId(), digitalCustomer.getName(), digitalCustomer.isPremiumAccount() ? "Premium" : "" + "      ", digitalCustomer.getBalance());//generate digitalCustomer cell
            System.out.println();
            digitalCustomer.displayInformation();
        }
    }

    public static boolean checkValidAccountNumber(String inputAccountNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return pattern.matcher(inputAccountNumber).find();
    }

    private static void inputAccount(String customerId, int number) {
        Customer customer = activeBank.getCustomerByID(customerId);
        System.out.println("Nhập mã STK gồm 6 chữ số: ");
        String accountNumber = sc.nextLine();
        Account checkAccountNumber = activeBank.findAccount(accountNumber);
        if (checkAccountNumber == null) {
            while (!checkValidAccountNumber(accountNumber) || customer.findAccount(accountNumber) != null) {
                System.out.println("Số tài khoản của người này đã tồn tại, hoặc không đủ 6 chữ số hoặc không là số, xin mời nhập lại");
                accountNumber = sc.nextLine();
                if (checkValidAccountNumber(accountNumber) && customer.findAccount(accountNumber) == null) break;
            }
            System.out.println("Nhap so du: ");
            double balance = Double.parseDouble(sc.nextLine());
            while (balance < 50_000) {
                System.out.println("Nhập ít nhất là 50000đ.....");
                balance = Double.parseDouble(sc.nextLine());
                if (balance >= 50_000) break;
            }
            Account account = number == 1 ? new SavingAccount(accountNumber, balance) : new LoanAccount(accountNumber, balance);
            if (activeBank.addAccount(customerId, account)) {
                System.out.println("Thêm tài khoản thành công");
                account.addTransaction(new Transaction(accountNumber, balance, false));
            } else {
                System.out.println("Thêm tài khoản không thành công");
            }
        } else {
            System.out.println("Số tài khoản này đã tồn tại trên hệ thống, xin mời nhập lại");
            inputAccount(customerId, number);
        }
    }
}

