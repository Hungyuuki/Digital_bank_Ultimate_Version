package vn.funix.FX22520.java.asm04.model;

import vn.funix.FX22520.java.asm04.dao.CustomerDao;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String bankId;
    private String bankName;

//    private List<DigitalCustomer> digitalCustomers;

//    public Bank() {
//        bankId = String.valueOf(UUID.randomUUID());
//        digitalCustomers = new ArrayList<>();
//    }

    public Bank(String bankId, String bankName) {
        this.bankId = bankId;
        this.bankName = bankName;
//        digitalCustomers = new ArrayList<>();
    }


//    public List<DigitalCustomer> getDigitalCustomers() {
//        return digitalCustomers;
//    }


//    public boolean addCustomer(String fileName) {
//        if (findCustomerById(newCustomer.getCustomerId()) == null) {//Nếu customer truyền vào ko tồn tại thì mới được add
//            digitalCustomers.add(newCustomer);
//            return true;
//        }
//        return false;
//    }

//    //Tìm customer bằng customerID, xem có
//    public DigitalCustomer findCustomerById(String customerId) {
//        for (int i = 0; i < this.digitalCustomers.size(); i++) {
//            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
//            if (checkedCustomer.getCustomerId().equals(customerId)) {//Nếu tìm thấy khách hàng có id này
//                return checkedCustomer; //thì trả về customer đó
//            }
//        }
//        return null;//không tìm thấy thì trả về null
//    }
//
//    public DigitalCustomer findCustomerByName(String customerName) {
//        for (int i = 0; i < this.digitalCustomers.size(); i++) {
//            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
//            if (checkedCustomer.getName().equals(customerName)) {//Nếu tìm thấy
//                return checkedCustomer; //thì trả về customer đó
//            }
//        }
//        return null;//không tìm thấy thì trả về null
//    }
//
//    public DigitalCustomer findCustomerByNameOrId(String nameOrId, int value) {
//        for (int i = 0; i < this.digitalCustomers.size(); i++) {
//            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
//            boolean check = value == 1 ? checkedCustomer.getName().equals(nameOrId) : checkedCustomer.getCustomerId().equals(nameOrId);
//            if (check) {
//                return checkedCustomer;
//            }
//        }
//        return null;
//    }
//
//    public Account findAccount(String accountNumber) {
//        for (int i = 0; i < this.digitalCustomers.size(); i++) {
//            Account checkedAccount = this.digitalCustomers.get(i).findAccount(accountNumber);
//            if (checkedAccount == null) break;
//            if (checkedAccount.getAccountNumber().equals(accountNumber)) {
//                return checkedAccount;
//            }
//        }
//        return null;
//    }
//
//    public boolean addAccount(String customerId, Account account) {
//        DigitalCustomer customer = findCustomerById(customerId);
//        if (customer != null) {
//            return customer.addAccount(account);
//        }
//        return false;
//    }
//
//
    public List<Customer> getCustomer() {
        return CustomerDao.read();
    }


    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}