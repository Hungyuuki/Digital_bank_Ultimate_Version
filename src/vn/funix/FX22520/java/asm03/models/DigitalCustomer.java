package vn.funix.FX22520.java.asm03.models;

//import vn.funix.FX22520.java.asm03.models.LoanAccount;

public class DigitalCustomer extends Customer {
    public DigitalCustomer(String name, String customerId) {
        super(name, customerId);
    }

    @Override
    public void displayInformation() {
        for (int i = 0; i < getAccounts().size(); i++) {
            System.out.printf("%15s | %20s | %20s | %10s", (i + 1) + "." + "   " + (getAccounts().get(i) instanceof SavingAccount ? "SAVINGS" : "LOAN"), getAccounts().get(i).getAccountNumber(), getAccounts().get(i).isPremiumAccount() ? "Premium" : "" + "      ", "    " + getAccounts().get(i).getBalance());
            System.out.println();
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        boolean isCheck = false;
        SavingAccount savingAccount;
        LoanAccount loanAccount;
        Account account = findAccount(accountNumber);
        Transaction transaction;
        if (account instanceof SavingAccount) {
            savingAccount = (SavingAccount) account;
            if (savingAccount.isAccepted(amount)) {
                savingAccount.withdraw(amount);
                savingAccount.log(amount);
                isCheck = true;
            }
        } else {
            loanAccount = (LoanAccount) account;
            if (loanAccount.isAccepted(amount)) {
                loanAccount.withdraw(amount);
                loanAccount.log(amount);
                isCheck = true;
            }
        }
        transaction = new Transaction(accountNumber, -amount, isCheck);
        account.addTransaction(transaction);
        return isCheck;
    }
}
