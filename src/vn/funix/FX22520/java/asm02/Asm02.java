package vn.funix.FX22520.java.asm02;

import vn.funix.FX22520.java.asm02.models.Account;
import vn.funix.FX22520.java.asm02.models.Bank;
import vn.funix.FX22520.java.asm02.models.Customer;

import java.util.Scanner;
import java.util.regex.Pattern;


public class Asm02 {
    public static final int EXIT_COMMAND_CODE = 0;
    public static final String AUTHOR = "FX22520";
    public static final String VERSION = "@v2.0.0";
    static Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        initMainMenu();
    }

    private static void initMainMenu() {
        //UI design
        while (true) {
            System.out.printf("%s\n", "+------------+--------------+------------+");
            System.out.println("|  NGAN HANG SO  |  " + AUTHOR + "  |  " + VERSION + "  |");
            System.out.printf("%s\n", "+------------+--------------+------------+");
            System.out.println("| 1. Thêm khách hàng                     |");
            System.out.println("| 2. Thêm tài khoản cho khách hàng       |");
            System.out.println("| 3. Hiển thị danh sách khách hàng       |");
            System.out.println("| 4. Tìm theo CCCD                       |");
            System.out.println("| 5. Tìm theo tên khách hàng             |");
            System.out.println("| 0. Thoát                               |");
            System.out.printf("%s\n", "+------------+--------------+------------+");
            System.out.println("Chức năng: ");
            initSomeObject();
            try {
                whenProgramIsRunning();
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Nhập số không nhập chữ.");
                whenProgramIsRunning();
            }
        }
    }

    public static boolean checkValidCustomerID(String inputCustomerID) {
        Pattern pattern = Pattern.compile("^[0-9]{12}$");
        return pattern.matcher(inputCustomerID).find();
    }

    public static boolean checkValidAccountNumber(String inputAccountNumber) {
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return pattern.matcher(inputAccountNumber).find();
    }

    public static void initSomeObject() {
        Customer songoku = new Customer("Songoku", "000000000000");
        Customer vegeta = new Customer("Vegeta", "111111111111");
        bank.addCustomer(songoku);
        bank.addCustomer(vegeta);
        Account accountSongoku = new Account("000000", 0);
        Account accountVegeta = new Account("111111", 10_000_000.0);
        Account accountVegetaPremium = new Account("222222", 60_000_000.0);
        bank.addAccount("000000000000", accountSongoku);
        bank.addAccount("111111111111", accountVegeta);
        bank.addAccount("111111111111", accountVegetaPremium);
    }

    public static void whenProgramIsRunning() {
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                initCustomer();
                break;
            case 2:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                addCustomerAccount();
                break;
            case 3:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                System.out.println("List customer: ");
                System.out.format("%15s%20s%20s%5s", "     CustomerID |     ", "   Customer Name |    ", " isPremiumAccount |    ", "Balance");
                //generate column name
                System.out.println();
                displayCustomerListByEachCustomer();
                break;
            case 4:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                System.out.println("Customer ID của bạn là gì? ");
                String customerID = sc.nextLine();
                System.out.println("List theo ID: ");
                findByCustomerNameOrCustomerId(bank.findCustomerByNameOrId(customerID, 0));
                break;
            case 5:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                System.out.println("Customer name của bạn là gì? ");
                String customerName = sc.nextLine();
                System.out.println("List theo tên: ");
                findByCustomerNameOrCustomerId(bank.findCustomerByNameOrId(customerName, 1));
                break;
            case 0:
                System.out.printf("%s\n", "+------------+--------------+------------+");
                exit();
        }
    }

    public static void exit() {
        System.exit(EXIT_COMMAND_CODE);
        System.out.println("Exit!!!");
        sc.close();
    }

    public static void addCustomerAccount() {//chức năng 2
        System.out.println("Nhập Căn cước công dân để xác thực: ");
        String customerId = sc.nextLine();
        while (!checkValidCustomerID(customerId) || bank.findCustomer(customerId) == null) {//Nếu chưa tồn tại số cccd này
            System.out.println("So can cuoc cong dan chua ton tai Không đúng định dạng, mời nhập lại số ID!");
            customerId = sc.nextLine();
            if (checkValidCustomerID(customerId) && bank.findCustomer(customerId) != null)
                break;//Nếu có tồn tại số cccd này rồi
        }
        inputAccount(customerId);
    }

    private static void inputAccount(String customerId) {
        Customer customer = bank.findCustomer(customerId);
        System.out.println("Nhập mã STK gồm 6 chữ số: ");
        String accountNumber = sc.nextLine();
        Account checkAccountNumber = bank.findAccount(accountNumber);
        if (checkAccountNumber==null) {
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
            Account account = new Account(accountNumber, balance);
            if (bank.addAccount(customerId, account)) {
                System.out.println("Thêm tài khoản thành công");
            } else {
                System.out.println("tài khoản không thành công");
            }
        } else {
            System.out.println("Số tài khoản này đã tồn tại trên hệ thống, xin mời nhập lại");
            inputAccount(customerId);
        }
    }


    public static void displayCustomerListByEachCustomer() {//Hiển thị khách hàng
        for (Customer customer : bank.getCustomer()) {
            System.out.printf("%15s | %20s | %20s | %10s", customer.getCustomerId(), customer.getName(), customer.isPremiumAccount() ? "Premium" : "" + "      ", customer.getBalance());//generate customer cell
            System.out.println();
            customer.displayInformation();
        }
    }

    public static void findByCustomerNameOrCustomerId(Customer customer) {//Chức năng 4 + 5
        if (customer != null) {
            System.out.println("Tài khoản của " + customer.getName() + " có id " + customer.getCustomerId() + " là:");
            customer.displayInformation();
        } else System.out.println("Không tìm thấy tên này");
    }

    public static void initCustomer() {//Chức năng 1
        System.out.println("Input customer name: ");
        String customerName = sc.nextLine();
        System.out.println("Input customer ID: ");
        try {
            inputCustomerID2(customerName);
        } catch (NumberFormatException numberFormatException) {
            System.err.println("Nhập số không nhập chữ.");
            inputCustomerID2(customerName);
        }
    }

    private static void inputCustomerID(String customerName) {
        String customerId = sc.nextLine();
        if (checkValidCustomerID(customerId)) {//Kiểm tra ID có đủ 12 số không
            for (int i = 0; i < bank.getCustomers().size(); i++) {
                Customer customer = new Customer(customerName, customerId);
                if (bank.addCustomer(customer)) {//Kiểm tra xem có customer đó ko, nếu ko có thì thêm
                    System.out.println("Thêm thành công");
                    initMainMenu();//hỏng chỗ này
                } else if (customerId.equals(bank.getCustomer().get(i).getCustomerId())) {//nếu có
                    System.out.println("Đã tồn tại customer ID này, mời nhập lại customer ID");
                    inputCustomerID(customerName);
                } else {
                    System.out.println("Không thêm được.");
                }
            }
        } else {
            System.out.println("Không đúng định dạng, mời nhập lại số ID!");
            inputCustomerID(customerName);
        }
    }

    private static void inputCustomerID2(String customerName) {//Chức năng 1
        String customerId = sc.nextLine();
        while ((!checkValidCustomerID(customerId)) || bank.findCustomer(customerId) != null) {
            System.out.println("Số customerId đã tồn tại hoặc không đủ 12 chữ số, vui lòng nhập lại");
            customerId = sc.nextLine();
            if (checkValidCustomerID(customerId) || (bank.findCustomer(customerId) == null)) break;
        }
        if (checkValidCustomerID(customerId) && (bank.findCustomer(customerId) == null)) {
            Customer customer = new Customer(customerName, customerId);
            if (bank.addCustomer(customer)) {//Kiểm tra xem có customer đó ko, nếu ko có thì thêm
                System.out.println("Thêm khách hàng thành công.");
            } else System.out.println("Đã tồn tại customerID này, không thêm được.");
        }
    }
}


