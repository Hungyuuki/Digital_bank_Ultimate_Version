package vn.funix.FX22520.java.asm04.model;

import vn.funix.FX22520.java.asm04.dao.AccountDao;
import vn.funix.FX22520.java.asm04.dao.CustomerDao;
import vn.funix.FX22520.java.asm04.dao.TransactionDao;
import vn.funix.FX22520.java.asm04.enums.TransactionType;
import vn.funix.FX22520.java.asm04.service.TextFileService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static vn.funix.FX22520.java.asm04.Asm04.checkIsExistingCustomerId;
import static vn.funix.FX22520.java.asm04.staticValue.Message.*;

public class DigitalBank extends Bank {

    public DigitalBank() {
        super("", "");
    }

    /**
     * Phương thức addCustomers(fileName) sẽ đọc dữ liệu từ file, dữ liệu từ file bao gồm nhiều khách hàng,
     * kiểm tra dữ liệu từng khách hàng có số ID hợp lệ hay không, nếu hợp lệ thì thêm vào danh sách,
     * nếu không hợp lệ hoặc số ID đã tồn tại thì hiển thị đoạn thông báo. Sau đó lưu dữ liệu customer vào file.
     *
     * @param fileName
     * @throws IOException
     */
    public void addCustomers(String fileName) throws IOException {//đã xong
        // Đọc danh sách khách hàng từ file TXT
        List<List<String>> readFileCustomer = TextFileService.readFile(fileName);

        // Tạo Set chứa các CustomerID đã tồn tại
        Set<String> existingFirstElements = customerList.stream()
                .map(Customer::getCustomerId)
                .collect(Collectors.toSet());

        List<Customer> customers = new ArrayList<>();

        // Xử lý danh sách khách hàng từ file TXT
        for (List<String> customerFile : readFileCustomer) {
            Customer customer = new Customer(customerFile.get(0).trim(), customerFile.get(1).trim());
            String customerId = customer.getCustomerId();
            if (checkCustomer(customerId, existingFirstElements)) customers.add(customer);
        }
        // Cập nhật danh sách khách hàng và lưu lại vào file nhị phân DAT
        if (!customers.isEmpty()) {
            customerList.addAll(customers);
            CustomerDao.save(customerList); // Lưu danh sách khách hàng cập nhật vào file DAT
        }
    }

    public boolean checkCustomer(String customerId, Set<String> existingFirstElements) {
        if (existingFirstElements.contains(customerId)) {
            System.out.println("Khách hàng mang số " + customerId + " đã tồn tại, không thêm vào file");
            return false;
        } else System.out.println("Khách hàng có số CCCD là " + customerId + " đã được thêm vào.");
        return true;
    }

    /**
     * Phương thức showCustomers() sẽ đọc dữ liệu từ file sử dụng hàm CustomerDao.list() để lấy danh sách khách hàng
     * và hiển thị thông qua hàm displayInformation. Nếu không có khách hàng nào trong danh sách thì hiển thị
     * “Chưa có khách hàng nào trong danh sách!”.
     */
    public void showCustomer() {
        for (Customer customer : customerList) {
            if (customer != null) {
                double totalBalance = 0;
                for (Account account : accountList) {
                    if (account.getCustomerId().equals(customer.getCustomerId())) {
                        totalBalance += account.getBalance(); // Cộng dồn số dư tài khoản
                    }
                }
                System.out.printf("%15s | %20s | %10s | %10s", customer.getCustomerId(), customer.getName(), (totalBalance >= 10000000 ? "Premium" : ""), totalBalance + "\n");
                // Kiểm tra customerID của account đó xem có phải customerID của người đó không
                for (int i = 0; i < accountList.size(); i++) {
                    Account account = accountList.get(i);
                    if (account.getCustomerId().equals(customer.getCustomerId())) {
                        System.out.printf("%15s | %20s | %10s | %10.2f\n", i + ". " + account.getAccountNumber(), "SAVINGS", "", account.getBalance());
                    }
                }
                System.out.printf("%s\n", "----------------------------------------------------------------");
            } else System.out.println("Chưa có khách hàng nào trong danh sách!");
        }
    }

    /**
     * Phương thức addSavingAccount(Scanner scanner, String customerId) để tạo mới một tài khoản ATM cho một khách hàng
     * và lưu tài khoản vào file.
     * Phương thức này cần kiểm tra customerId hợp lệ, sau đó gọi phương thức thêm tài khoản mới của đối tượng customer.
     */

    public void addSavingsAccount(Scanner sc, String customerId) throws IOException {
        //đọc file nhị phân để so sánh xem customerId vừa nhập có bằng ID trong customerList không, nếu có thì vào tiếp, ko có thì hiện thông báo
//        List<Customer> customerList = CustomerDao.read();
        checkIsExistingCustomerId(customerId);
        //nhập stk ngân hàng
        System.out.print("Nhập số tài khoản ngân hàng: ");
        String accountNumber = sc.nextLine().trim();
        List<Account> accountNumberList = AccountDao.read();
        Set<String> existing = accountNumberList.stream().map(Account::getAccountNumber)
                .collect(Collectors.toSet());
        while (existing.contains(accountNumber)) {
            System.out.println("Đã có stk " + accountNumber + ", mời nhập lại số tk khác.");
            accountNumber = sc.nextLine().trim();
            if (!existing.contains(accountNumber)) {
                System.out.println("số tk hợp lệ");
                break;
            }
        }
        System.out.println("Nhập số dư tài khoản >= 50000đ");
        double balance = sc.nextDouble();
        while (balance < MIN_BALANCE) {
            System.out.println("Số bạn vừa nhập nhỏ hơn " + MIN_BALANCE + ", xin mời nhập lại");
            balance = sc.nextDouble();
            if (balance > MIN_BALANCE) break;
        }
        //đọc file accounts.dat cho trước để kiểm tra xem đã có account nào của người nào chưa, lưu vào 1 biến accountList

        Account newAccount = new Account(customerId, accountNumber, balance);
        if (!isAccountExisted(accountList, newAccount)) {// nếu newAccount ko có trong accountList thì update
            AccountDao.update(newAccount);//hàm này có để làm gì?
        }
        accountList.add(newAccount);
        //newAccount lúc này chỉ là phần tử đầu tiên của accountList
        System.out.printf("%15s | %20s | %10s", accountList.get(0), "", "\n");
        AccountDao.save(accountList); // Lưu danh sách khách hàng cập nhật vào file DAT
        System.out.println("Đã thêm tài khoản " + accountNumber + " cho khách hàng: " + customerId + ", số dư là " + balance);
        //tạo transaction mới để lưu vào làm giao dịch deposit
        Transaction newTransaction = new Transaction(accountNumber, balance, true, TransactionType.DEPOSIT);
        transactionList.add(newTransaction);// đọc giao dịch mới rồi thêm vào danh sách đọc
        TransactionDao.save(transactionList);//thêm giao dịch vừa đọc được vào danh sách ghi
    }

    public boolean withdraw(Scanner scanner, String customerId) {
        return false;
    }

//    public boolean withdraw(String customerId, String accountNumber, double amount) {
//        DigitalCustomer digitalCustomer = getCustomerById(customerId);
//        if (digitalCustomer != null) return digitalCustomer.withdraw(accountNumber, amount);
//        return false;
//    }

    public Customer getCustomerById(String customerId) {
        for (Customer checkedCustomer : customerList) {
            if (checkedCustomer.getCustomerId().equals(customerId)) {
                return checkedCustomer;
            }
        }
        return null;
    }

    public void transfer(Scanner scanner, String customerId) {

    }

    public boolean isAccountExisted(List<Account> accountsList, Account newAccount) {
        return newAccount.equals(accountsList);
    }

    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        return false;
    }

    public Customer getCustomerById(List<Customer> customerList, String customerId) {
        return null;
    }

    public boolean withdraw(String customerId, String accountNumber, double amount) throws ClassCastException, IOException {
        TransactionType type = TransactionType.WITHDRAW;
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            System.out.println("tìm thấy customer");
            return customer.withdraw(accountNumber, amount, type);
        } else {
            System.out.println("Không tìm thấy customer với ID: " + customerId);
            return false;
        }
    }

    public boolean transfer(String customerId, String transferAccountNumber, String receiveAccountNumber, double amount) throws ClassCastException, IOException {
        TransactionType type = TransactionType.TRANSFER;
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            System.out.println("tìm thấy customer");
            return customer.transfer(transferAccountNumber, receiveAccountNumber, amount, type);
        } else {
            System.out.println("Không tìm thấy customer với ID: " + customerId);
            return false;
        }
    }
}
