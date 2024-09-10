package vn.funix.FX22520.java.asm04.dao;

import vn.funix.FX22520.java.asm04.model.Customer;
import vn.funix.FX22520.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

import static vn.funix.FX22520.java.asm04.staticValue.Message.FILE_CUSTOMERS_DAT;

public class CustomerDao {
    public static void save (List<Customer> customerList) throws IOException {
        BinaryFileService.writeFile(FILE_CUSTOMERS_DAT, customerList);
    }//Nhận vào 1 list customer, ghi list customer vào file .dat
    public static List<Customer> read() {
        return BinaryFileService.readFile(FILE_CUSTOMERS_DAT);
    }
    //đọc file .dat Customer List rồi đối chiếu như dùng lớp bình thường

}
