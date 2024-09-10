package vn.funix.FX22520.java.asm03.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String id;
    private List<DigitalCustomer> digitalCustomers;

    public Bank() {
        id = String.valueOf(UUID.randomUUID());
        digitalCustomers = new ArrayList<>();
    }

    public List<DigitalCustomer> getDigitalCustomers() {
        return digitalCustomers;
    }


    public boolean addCustomer(DigitalCustomer newCustomer) {
        if (findCustomerById(newCustomer.getCustomerId()) == null) {//Nếu customer truyền vào ko tồn tại thì mới được add
            digitalCustomers.add(newCustomer);
            return true;
        }
        return false;
    }

    //Tìm customer bằng customerID, xem có
    public DigitalCustomer findCustomerById(String customerId) {
        for (int i = 0; i < this.digitalCustomers.size(); i++) {
            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
            if (checkedCustomer.getCustomerId().equals(customerId)) {//Nếu tìm thấy khách hàng có id này
                return checkedCustomer; //thì trả về customer đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    public DigitalCustomer findCustomerByName(String customerName) {
        for (int i = 0; i < this.digitalCustomers.size(); i++) {
            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
            if (checkedCustomer.getName().equals(customerName)) {//Nếu tìm thấy
                return checkedCustomer; //thì trả về customer đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    public DigitalCustomer findCustomerByNameOrId(String nameOrId, int value) {
        for (int i = 0; i < this.digitalCustomers.size(); i++) {
            DigitalCustomer checkedCustomer = this.digitalCustomers.get(i);
            boolean check = value == 1 ? checkedCustomer.getName().equals(nameOrId) : checkedCustomer.getCustomerId().equals(nameOrId);
            if (check) {
                return checkedCustomer;
            }
        }
        return null;
    }

    public Account findAccount(String accountNumber) {
        for (int i = 0; i < this.digitalCustomers.size(); i++) {
            Account checkedAccount = this.digitalCustomers.get(i).findAccount(accountNumber);
            if (checkedAccount == null) break;
            if (checkedAccount.getAccountNumber().equals(accountNumber)) {
                return checkedAccount;
            }
        }
        return null;
    }

    public boolean addAccount(String customerId, Account account) {
        DigitalCustomer customer = findCustomerById(customerId);
        if (customer != null) {
            return customer.addAccount(account);
        }
        return false;
    }


    public List<DigitalCustomer> getCustomer() {
        return digitalCustomers;
    }


}