package vn.funix.FX22520.java.asm04.model;
public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }


    @Override
    public void displayInformation() {
        for (int i = 0; i < getAccounts().size(); i++) {
            System.out.printf("%15s | %20s | %20s | %10s", (i + 1) + "." + "   " + (getAccounts().get(i) instanceof SavingsAccount ? "SAVINGS" : "LOAN"), getAccounts().get(i).getAccountNumber(), getAccounts().get(i).isPremiumAccount() ? "Premium" : "" + "      ", "    " + getAccounts().get(i).getBalance());
            System.out.println();
        }
    }
}
