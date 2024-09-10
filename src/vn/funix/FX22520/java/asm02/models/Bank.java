package vn.funix.FX22520.java.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String id;
    private List<Customer> customers;

    public Bank() {
        id = String.valueOf(UUID.randomUUID());
        customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

//    Phương thức có tham số là newCustomer: Customer, dùng để thêm khách hàng mới cho ngân hàng.
//    Khách hàng được thêm khi và chỉ khi số CCCD của khách hàng chưa từng tồn tại trước đó.
    public boolean addCustomer(Customer newCustomer) {
        if(findCustomer(newCustomer.getCustomerId())==null) {//Nếu customer truyền vào ko tồn tại thì mới được add
            customers.add(newCustomer);
            return true;
        }
        return false;
    }

    //Tìm customer bằng customerID, xem có
    public Customer findCustomer(String customerId) {
        for (int i = 0; i < this.customers.size(); i++) {
            Customer checkedCustomer = this.customers.get(i);
            if (checkedCustomer.getCustomerId().equals(customerId)) {//Nếu tìm thấy
                return checkedCustomer; //thì trả về customer đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    public Customer findCustomerByName(String customerName) {
        for (int i = 0; i < this.customers.size(); i++) {
            Customer checkedCustomer = this.customers.get(i);
            if (checkedCustomer.getName().equals(customerName)) {//Nếu tìm thấy
                return checkedCustomer; //thì trả về customer đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    public Customer findCustomerByNameOrId(String nameOrId, int value) {
        for (int i = 0; i < this.customers.size(); i++) {
            Customer checkedCustomer = this.customers.get(i);
            boolean check = value==1 ? checkedCustomer.getName().equals(nameOrId) : checkedCustomer.getCustomerId().equals(nameOrId);
            if (check) {//Nếu tìm thấy
                return checkedCustomer; //thì trả về customer đó
            }
        }
        return null;//không tìm thấy thì trả về null
    }

    public Account findAccount(String accountNumber){
        for (int i = 0; i < this.customers.size(); i++) {
            Account checkedAccount = this.customers.get(i).findAccount(accountNumber);
            if (checkedAccount == null) break;
            if(checkedAccount.getAccountNumber().equals(accountNumber)) {//Nếu tìm thấy
                return checkedAccount; //thì trả về account đó
            }
        }
        return null;//không tìm thấy tk = stk thì trả về null
    }

    public boolean addAccount(String customerId, Account account) {
        Customer customer = findCustomer(customerId);
        if (customer!=null){
            return customer.addAccount(account);
        }
        return false;
    }


    public List<Customer> getCustomer() {
        return customers;
    }
}
