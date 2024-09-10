package vn.funix.FX22520.java.asm04.staticValue;

import vn.funix.FX22520.java.asm04.dao.AccountDao;
import vn.funix.FX22520.java.asm04.dao.CustomerDao;
import vn.funix.FX22520.java.asm04.dao.TransactionDao;
import vn.funix.FX22520.java.asm04.model.Account;
import vn.funix.FX22520.java.asm04.model.Customer;
import vn.funix.FX22520.java.asm04.model.DigitalBank;
import vn.funix.FX22520.java.asm04.model.Transaction;

import java.util.List;

public class Message {
    public static final String FILE_CUSTOMERS_TXT = "store\\Customers.txt"; // Đường dẫn đến file cần đọc//sau này là để thêm
    public static final String FILE_CUSTOMERS_DAT = "store\\Customers.dat";//Đường dẫn tới file cần ghi
    public final static String FILE_ACCOUNT_DAT = "store\\Accounts.dat";
    public final static String FILE_TRANSACTION_DAT = "store\\Transactions.dat";
    public static final DigitalBank activeBank = new DigitalBank();
    public static final List<Account> accountList = AccountDao.read();//đường dẫn tới file cần đọc
    public static final List<Customer> customerList = CustomerDao.read();
    public static final List<Transaction> transactionList = TransactionDao.read();
    public static final String AUTHOR = "FX22520";
    public static final String VERSION = "FX22520@v4.0.0";
    public static final int EXIT_COMMAND_CODE = 0;
    public static final double MIN_BALANCE = 50000D;

    public static final String COMMA_DELIMITER = ", ";
}
