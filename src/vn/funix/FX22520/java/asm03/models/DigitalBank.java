package vn.funix.FX22520.java.asm03.models;

public class DigitalBank extends Bank {
    public DigitalBank() {
    }

    public DigitalCustomer getCustomerByID(String customerId) {
        for (int i = 0; i < super.getCustomer().size(); i++) {
            DigitalCustomer checkedCustomer = super.getCustomer().get(i);
            if (checkedCustomer.getCustomerId().equals(customerId)) {
                return checkedCustomer;
            }
        }
        return null;
    }

    public void addCustomer(String customerId, String name) {
        DigitalCustomer newCustomer = getCustomerByID(customerId);
        if (newCustomer == null) {
            newCustomer = new DigitalCustomer(name, customerId);
            this.getDigitalCustomers().add(newCustomer);
        }
    }

    public boolean withdraw(String customerId, String accountNumber, double amount) {
        DigitalCustomer digitalCustomer = getCustomerByID(customerId);
        if (digitalCustomer != null) return digitalCustomer.withdraw(accountNumber, amount);
        return false;
    }
}
