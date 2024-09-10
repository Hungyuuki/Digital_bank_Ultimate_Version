package vn.funix.FX22520.java.asm04.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService {

    //        định nghĩa một phương thức generics readFile writeFile, được dùng lại trong các class DAO
    public static <T> List<T> readFile(String fileName) {
//        <T>: Sử dụng Generics để cho phép phương thức làm việc với bất kỳ kiểu đối tượng nào.
//        List<T>: Phương thức trả về một danh sách các đối tượng kiểu T.
//        Phương thức này có thể tạo ra các object kiểu T rồi đưa vào 1 List
        List<T> objects = new ArrayList<>();
        //đọc file dưới này
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    Object object = file.readObject();//đọc được các object kiểu T
                    objects.add((T) object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();//nếu ngay từ đầu mà file đã không có gì để đọc thì sẽ nhảy vàongoafiaij lệ này để trả ra một list rỗng
        } catch (IOException e) {
            System.out.println("IO exception " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException: " + e.getMessage());
        }
        return objects; // còn đây là khi không gặp ngoại lên EOFException thì trả về một list các object như bình thường
    }

        public static <T> void writeFile(String fileName, List<T> objects) {
        //ghi file ở đây
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object :
                    objects) {
                file.writeObject(object);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
