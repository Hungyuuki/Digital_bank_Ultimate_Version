package vn.funix.FX22520.java.asm04;

import vn.funix.FX22520.java.asm04.dao.AccountDao;
import vn.funix.FX22520.java.asm04.dao.TransactionDao;
import vn.funix.FX22520.java.asm04.enums.MenuType;
import vn.funix.FX22520.java.asm04.enums.TransactionType;
import vn.funix.FX22520.java.asm04.model.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static vn.funix.FX22520.java.asm04.staticValue.Message.*;

public class Asm04 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        initMainMenu();
    }

    private static void initMainMenu() throws IOException {
        while (true) {
            //UI design
            System.out.printf("%s\n", "+---------------+----------------+---------------+");
            System.out.println("|  NGAN HANG SO  |  " + AUTHOR + "   |   " + VERSION + "  |");
            System.out.printf("%s\n", "+---------------+----------------+---------------+");
            System.out.printf("%s", "| 1. Xem danh sách khách hàng.                    |\n");
            System.out.printf("%s", "| 2. Nhập danh sách khách hàng.                   |\n");
            System.out.printf("%s", "| 3. Thêm tài khoản ATM.                          |\n");
            System.out.printf("%s", "| 4. Chuyển tiền.                                 |\n");
            System.out.printf("%s", "| 5. Rút tiền.                                    |\n");
            System.out.printf("%s", "| 6. Tra cứu lịch sử giao dịch.                   |\n");
            System.out.printf("%s", "| 0. Thoát.                                       |\n");
            System.out.printf("%s\n", "+---------------+----------------+---------------+");
            System.out.println("Chức năng: ");
            try {
                whenProgramIsRunning();
            } catch (NumberFormatException numberFormatException) {
                System.err.println("Nhập số không nhập chữ.");
                whenProgramIsRunning();
            }
        }
    }

    public static void whenProgramIsRunning() throws IOException {
        int input = Integer.parseInt(sc.nextLine().trim());
        MenuType menuType = MenuType.getInputMenu(input);
        if (menuType == null) {
            return;
        }
        switch (menuType) {
            case VIEW_CUSTOMERS://1
                viewCustomers();
                break;
            case ADD_CUSTOMERS://2
                addCustomer();
                break;
            case ADD_SAVINGS_ACCOUNT://3
                addSavingsAccount();
                break;
            case TRANSFER://4
                transfer();
                break;
            case WITHDRAW://5
                withdraw();
                break;
            case HISTORY://6
                history();
                break;
            case EXIT://0
                System.out.println("Thoát");
                System.exit(EXIT_COMMAND_CODE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + input);
        }
    }

    private static void history() {
        System.out.println("Nhập mã số khách hàng");
        String customerId = sc.nextLine().trim();

        if (checkIsExistingCustomerId(customerId)) {
            boolean foundCustomer = false;
            for (Customer customer : customerList) {
                if (customer != null && customerId.equals(customer.getCustomerId())) {
                    foundCustomer = true;
                    double totalBalance = 0;
                    for (Account account : accountList) {
                        if (account.getCustomerId().equals(customer.getCustomerId())) {
                            totalBalance += account.getBalance(); // Cộng dồn số dư tài khoản
                        }
                    }
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    System.out.printf("%s\n", "----------------------------------------------------------------");
                    System.out.printf("%-15s | %-15s | %15s | %30s\n", customer.getCustomerId(), customer.getName(), (totalBalance >= 10000000 ? "Premium" : ""), formatter.format((long) totalBalance));
                    for (int i = 0; i < accountList.size(); i++) {
                        Account account = accountList.get(i);
                        if (account.getCustomerId().equals(customerId)) {
                            System.out.printf("%-15s | %-15s | %15s | %30s\n", i + ". " + account.getAccountNumber(), account.getAccountType(), "", formatter.format((long) account.getBalance()));
                        }
                    }
                    System.out.printf("%s\n", "----------------------------------------------------------------");
                    for (Transaction transaction : transactionList) {
                        for (Account account : accountList) {
                            // Kiểm tra nếu giao dịch thuộc về tài khoản của khách hàng
                            if (account.getCustomerId().equals(customerId) && transaction.getAccountNumber().equals(account.getAccountNumber())) {
                                System.out.printf("%-15s | %-15s | %15s | %30s\n", "[GD] " + transaction.getAccountNumber(), transaction.getType(), formatter.format((long) (transaction.getAmount())), transaction.getTime());
                            }
                        }
                    }
                }
            }
            if (!foundCustomer) {
                System.out.println("Không có tài khoản nào liên kết với khách hàng này.");
            }
        } else {
            System.out.println("Không có khách hàng này");
        }
    }

    private static void withdraw() throws IOException {
        System.out.println("Nhập mã số khách hàng:");
        String customerId = sc.nextLine().trim();
        if (checkIsExistingCustomerId(customerId)) {
            System.out.println("Nhập số tài khoản cần rút tiền");
            String withdrawNumberAccount = sc.nextLine().trim();
            Set<String> existing = accountList.stream().map(Account::getAccountNumber).collect(Collectors.toSet());
            while (!existing.contains(withdrawNumberAccount)) {
                System.out.println("Chưa có stk này " + withdrawNumberAccount + ", mời nhập lại số tk khác.");
                withdrawNumberAccount = sc.nextLine().trim();
                if (existing.contains(withdrawNumberAccount)) {
                    System.out.println("Welcome " + customerId + "!");
                    break;
                }
            }
            System.out.println("Nhập số tiền cần rút");
            double withdrawAmount = Double.parseDouble(sc.nextLine());
            if (activeBank.withdraw(customerId, withdrawNumberAccount, withdrawAmount)) {
                System.out.println("Rút tiền thành công");
                Account withdrawAccount = new Account(customerId, withdrawNumberAccount, withdrawAmount);
                Transaction withdrawalTransaction = new Transaction(withdrawNumberAccount, withdrawAmount, true, TransactionType.WITHDRAW);
                withdrawAccount.addTransaction(withdrawalTransaction);
                System.out.printf("%20s | %15s | %10s | %10s", withdrawalTransaction.getAccountNumber(), "-" + withdrawalTransaction.getAmount(), withdrawalTransaction.getTime().trim(), withdrawalTransaction.getType() + "\n");
                AccountDao.update(withdrawAccount);
                AccountDao.save(accountList);
                transactionList.add(withdrawalTransaction);// đọc giao dịch mới rồi thêm vào danh sách đọc
                TransactionDao.save(transactionList);//thêm giao dịch vừa đọc được vào danh sách ghi
            } else {
                System.out.println("Không thành công");
            }
        }
    }

    private static void transfer() throws IOException {
        System.out.println("Nhập mã số khách hàng:");
        String customerId = sc.nextLine().trim();
        if (checkIsExistingCustomerId(customerId)) {
            System.out.println("Nhập số tài khoản gửi");
            String transferNumberAccount = sc.nextLine().trim();
            Set<String> existing = accountList.stream().map(Account::getAccountNumber).collect(Collectors.toSet());

            while (!existing.contains(transferNumberAccount)) {
                System.out.println("Chưa có stk người gửi " + transferNumberAccount + ", mời nhập lại số tk khác.");
                transferNumberAccount = sc.nextLine().trim();
                if (existing.contains(transferNumberAccount)) {
                    System.out.println("Số tk hợp lệ, xin mời nhập stk người nhận!");
                    break;
                }
            }
            System.out.println("Nhập số tài khoản nhận");
            String receiveNumberAccount = sc.nextLine().trim();
            String foundCustomer = getCustomerIdByAccountNumber(receiveNumberAccount);
            if (!foundCustomer.isEmpty()) {
                System.out.println("Số tk nhận này là của khách hàng " + foundCustomer + ", ");
            }
            while (!existing.contains(receiveNumberAccount)) {
                System.out.println("Chưa có stk người nhận " + receiveNumberAccount + ", mời nhập lại số tk khác.");
                receiveNumberAccount = sc.nextLine().trim();
                if (existing.contains(receiveNumberAccount)) {
                    System.out.println("Số tk hợp lệ, xin mời nhập số tiền cần chuyển");
                    break;
                }
            }
            System.out.println("Nhập số tiền cần chuyển");
            double transferAmount = Double.parseDouble(sc.nextLine());
            //Bây giờ check xem số tiền cần chuyển có thỏa mãn điều kiện chuyển tiền không, gọi hàm isAccepted nếu true thì gọi hàm withdraw
            //hàm withdraw này sẽ nhận các tham số như bên dưới để khởi tạo giao dịch mới
            if (activeBank.transfer(customerId, transferNumberAccount, receiveNumberAccount, transferAmount)) {
                //trong giao dịch chuyển tiền này, người gửi sẽ bị trừ đi amount, người nhận sẽ cộng vào amount
                System.out.println("Transfer thành công");
                Account transferAccount = new Account(foundCustomer, transferNumberAccount, transferAmount);
                Transaction transaction = new Transaction(transferNumberAccount, transferAmount, true, TransactionType.TRANSFER);
                transferAccount.addTransaction(transaction);
                AccountDao.update(transferAccount);
                System.out.printf("%20s | %15s | %10s | %10s", transaction.getAccountNumber(), "-" + transaction.getAmount(), transaction.getTime().trim(), transaction.getType() + "\n");
                Account receiveAccount = new Account(foundCustomer, receiveNumberAccount, -transferAmount);
                AccountDao.update(receiveAccount);
                Transaction receive = new Transaction(receiveNumberAccount, transferAmount, true, TransactionType.TRANSFER);
                receiveAccount.addTransaction(receive);
                System.out.printf("%20s | %15s | %10s | %10s", receive.getAccountNumber(), "+" + receive.getAmount(), receive.getTime().trim(), receive.getType() + "\n");
                TransactionDao.save(transactionList);
                AccountDao.save(accountList);
                transactionList.add(transaction);// đọc giao dịch mới rồi thêm vào danh sách đọc
                transactionList.add(receive);// đọc giao dịch mới rồi thêm vào danh sách đọc
                TransactionDao.save(transactionList);
            } else {
                System.out.println("Không thành công");
            }
        }
    }

    public static String getCustomerIdByAccountNumber(String accountNumber) {
        // Sử dụng stream để tìm tài khoản có số tài khoản trùng khớp
        Optional<Account> accountOptional = accountList.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();

        // Nếu tìm thấy tài khoản, trả về customerId, ngược lại trả về null hoặc thông báo không tìm thấy
        if (accountOptional.isPresent()) {
            return accountOptional.get().getCustomerId();
        } else {
            System.out.println("Không tìm thấy tài khoản với số tài khoản: " + accountNumber);
            return null;
        }
    }

    public static boolean checkIsExistingCustomerId(String customerId) {
        Set<String> existingFirstElements = customerList.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toSet());
        if (!existingFirstElements.contains(customerId)) {
            System.out.println("Chưa có customerId này, xin mời nhập lại.");
            return false;
        } else {
            System.out.println("CustomerId hợp lệ.");
            return true;
        }
    }

    private static void addSavingsAccount() throws IOException {
        System.out.println("Nhập mã số khách hàng:");
        String customerId = sc.nextLine().trim();
        DigitalBank digitalBank = new DigitalBank();
        digitalBank.addSavingsAccount(sc, customerId);
    }

    private static void addCustomer() throws IOException {
        System.out.println("Nhập danh sách khách hàng.");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Mời nhập đường dẫn tới file thêm khách hàng");
        System.out.println("Copy this path: " + FILE_CUSTOMERS_TXT);
        String path = sc.next();
        while (!(Objects.equals(path, FILE_CUSTOMERS_TXT)) || !checkValidPath(path)) {
            System.out.println("Không đúng định dạng đường dẫn, mời nhập đúng đường dẫn");
            path = sc.next();
            if (Objects.equals(path, FILE_CUSTOMERS_TXT) && checkValidPath(path)) break;
        }
        activeBank.addCustomers(FILE_CUSTOMERS_TXT);
    }

    private static void viewCustomers() {
        System.out.println("Xem danh sách khách hàng.");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%15s | %20s | %10s | %10s", "CustomerID", "FullName", "isPremium", "Balance\n");
        System.out.println("----------------------------------------------------------------");
        activeBank.showCustomer();
    }

    public static boolean checkValidPath(String path) {
        return Pattern.matches(".*\\.txt", path);
    }
}

