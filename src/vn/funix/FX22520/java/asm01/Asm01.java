package vn.funix.FX22520.java.asm01;

import java.util.*;
import java.util.regex.Pattern;


public class Asm01 {
    public static final String AUTHOR = "FX22520";
    public static final String VERSION = "@v1.0.0";

    static ArrayList<String> provinceName;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> provinceCode;

    static Map<String, List<String>> cemterMap = new HashMap<>();

    public static void main(String[] args) {
        initCenter();
        initData();
        initMainMenu();
        //run
        try {
            whenProgramIsRunning();
        }
        catch (InputMismatchException exception) {
            System.out.println("Ngoại lệ: Đầu vào không khớp");
            whenProgramIsRunning();

        }
        catch (NumberFormatException exception) {
            System.err.println("Ngoại lệ: Nhập số chứ không nhập chữ");
            whenProgramIsRunning();

        }
        catch (StackOverflowError exception) {
            System.out.println("Ngoại lệ: Tràn bộ nhớ");
            whenProgramIsRunning();
        }
    }
    private static void initMainMenu() {
        //UI design
        System.out.printf("%s\n", "+-----------+--------------+-----------+");
        System.out.println("| NGAN HANG SO  | " + AUTHOR + " | " + VERSION + "   |");
        System.out.printf("%s\n", "+-----------+--------------+-----------+");
        System.out.println("| 1. Nhap CCCD                         |");
        System.out.println("| 0. Thoat                             |");
        System.out.printf("%s\n", "+-----------+--------------+-----------+");

    }

    private static void initData() {// lưu vào để tạo 1 lần.

        provinceName = new ArrayList<>();
        provinceCode = new ArrayList<>();
        //add provinceName
        provinceName.add("Hà Nội");
        provinceName.add("Hà Giang");
        provinceName.add("Cao Bằng");
        provinceName.add("Bắc Kạn");
        provinceName.add("Tuyên Quang");
        provinceName.add("Lào Cai");
        provinceName.add("Điện Biên");
        provinceName.add("Lai Châu");
        provinceName.add("Sơn La");
        provinceName.add("Yên Bái");
        provinceName.add("Hòa Bình");
        provinceName.add("Thái Nguyên");
        provinceName.add("Lạng Sơn");
        provinceName.add("Quảng Ninh");
        provinceName.add("Bắc Giang");
        provinceName.add("Phú Thọ");
        provinceName.add("Vĩnh Phúc");
        provinceName.add("Bắc Ninh");
        provinceName.add("Hải Dương");
        provinceName.add("Hải Phòng");
        provinceName.add("Hưng Yên");
        provinceName.add("Thái Bình");
        provinceName.add("Hà Nam");
        provinceName.add("Nam Định");
        provinceName.add("Ninh Bình");
        provinceName.add("Thanh Hóa");
        provinceName.add("Nghệ An");
        provinceName.add("Hà Tĩnh");
        provinceName.add("Quảng Bình");
        provinceName.add("Quảng Trị");
        provinceName.add("Thừa Thiên - Huế");
        provinceName.add("Đà Nẵng");
        provinceName.add("Quảng Nam");
        provinceName.add("Quảng Ngãi");
        provinceName.add("Bình Định");
        provinceName.add("Phú Yên");
        provinceName.add("Khánh Hòa");
        provinceName.add("Ninh Thuận");
        provinceName.add("Bình Thuận");
        provinceName.add("Kon Tum");
        provinceName.add("Gia Lai");
        provinceName.add("Đắk Lắk");
        provinceName.add("Đắk Nông");
        provinceName.add("Lâm Đồng");
        provinceName.add("Bình Phước");
        provinceName.add("Tây Ninh");
        provinceName.add("Bình Dương");
        provinceName.add("Đồng Nai");
        provinceName.add("Bà Rịa - Vũng Tàu");
        provinceName.add("Hồ Chí Minh");
        provinceName.add("Long An");
        provinceName.add("Tiền Giang");
        provinceName.add("Bến Tre");
        provinceName.add("Trà Vinh");
        provinceName.add("Vĩnh Long");
        provinceName.add("Đồng Tháp");
        provinceName.add("An Giang");
        provinceName.add("Kiên Giang");
        provinceName.add("Cần Thơ");
        provinceName.add("Hậu Giang");
        provinceName.add("Sóc Trăng");
        provinceName.add("Bạc Liêu");
        provinceName.add("Cà Mau");
        
        //add provinceCode
        provinceCode.add("001");
        provinceCode.add("002");
        provinceCode.add("004");
        provinceCode.add("006");
        provinceCode.add("008");
        provinceCode.add("010");
        provinceCode.add("011");
        provinceCode.add("012");
        provinceCode.add("014");
        provinceCode.add("015");
        provinceCode.add("017");
        provinceCode.add("019");
        provinceCode.add("020");
        provinceCode.add("022");
        provinceCode.add("024");
        provinceCode.add("025");
        provinceCode.add("026");
        provinceCode.add("027");
        provinceCode.add("030");
        provinceCode.add("031");
        provinceCode.add("033");
        provinceCode.add("034");
        provinceCode.add("035");
        provinceCode.add("036");
        provinceCode.add("037");
        provinceCode.add("038");
        provinceCode.add("040");
        provinceCode.add("042");
        provinceCode.add("044");
        provinceCode.add("045");
        provinceCode.add("046");
        provinceCode.add("048");
        provinceCode.add("049");
        provinceCode.add("051");
        provinceCode.add("052");
        provinceCode.add("054");
        provinceCode.add("056");
        provinceCode.add("058");
        provinceCode.add("060");
        provinceCode.add("062");
        provinceCode.add("064");
        provinceCode.add("066");
        provinceCode.add("067");
        provinceCode.add("068");
        provinceCode.add("070");
        provinceCode.add("072");
        provinceCode.add("074");
        provinceCode.add("075");
        provinceCode.add("077");
        provinceCode.add("079");
        provinceCode.add("080");
        provinceCode.add("082");
        provinceCode.add("083");
        provinceCode.add("084");
        provinceCode.add("086");
        provinceCode.add("087");
        provinceCode.add("089");
        provinceCode.add("091");
        provinceCode.add("092");
        provinceCode.add("093");
        provinceCode.add("094");
        provinceCode.add("095");
        provinceCode.add("096");
    }

    public static void whenProgramIsRunning() {
        System.out.print("Type your choose: ");
        try {
            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1:
                    generatePincodeMenu();
                    break;
                case 0:
                    whenClicked2OrExit();
                    break;
                default:
                    System.out.println("Nhập lại");
                    whenProgramIsRunning();
            }
        } catch (InputMismatchException exception) {
            System.out.println("Ngoại lệ: Đầu vào không khớp");
            whenProgramIsRunning();
        } catch (NumberFormatException exception) {
            System.out.println("Ngoại lệ: Nhập số 1 hoặc 0 chứ không nhập chữ");
            whenProgramIsRunning();
        }

        sc.close();
    }

    public static void generatePincodeMenu() {
        System.out.println("Pincode Easily or Hardly:");
        System.out.println("    | 1. Easy");
        System.out.println("    | 2. Hard");
        choosePincodeMenu();
    }
    public static void choosePincodeMenu() {
        System.out.print("Type your choose: ");
        try {
            int inputEasyOrHard = Integer.parseInt(sc.nextLine());
            switch (inputEasyOrHard) {
                case 1:
                    int randomCode = (int) (Math.random() * (999 - 100 + 1) + 100);//Randomly generate a value 100-9999
                    typeNumericPinCode(randomCode);
                    break;
                case 2:
                    String randomString = generateRandomString();//Randomly generate an alphanumeric code
                    typeAlphaNumericPinCode(randomString);
                    break;
                default:
                    System.out.println("Mời chọn lại");
                    generatePincodeMenu();
            }
        } catch (NumberFormatException exception) {
            System.out.println("Ngoại lệ: Nhập số 1 hoặc 2 chứ không nhập chữ");
            choosePincodeMenu();
        } catch (InputMismatchException exception) {
            System.out.println("Ngoại lệ: Đầu vào không khớp");
            choosePincodeMenu();
        }
    }

    public static void typeAlphaNumericPinCode(String randomString){//hard pin code
        System.out.println("Input your code: " + randomString);
        String alphaNumberInput = sc.next();
        if (!alphaNumberInput.equals(randomString)) {
            System.out.println("Invalid code, please try again exactly " + randomString);
            typeAlphaNumericPinCode(randomString);
        } else typeInputID();
    }
    public static void typeNumericPinCode(int randomCode) {//easy pin code
        System.out.println("Input your code: " + randomCode);
        int numberInput = sc.nextInt();
        if (numberInput != randomCode) {
            System.out.println("Invalid code, please try again exactly " + randomCode);
            typeNumericPinCode(randomCode);
        } else typeInputID();
    }
    public static String typeInputID () {
        System.out.println("Input your ID: ");
        String inputID = sc.next();
        checkInputID(inputID);
        return inputID;
    }

    public static void checkInputID(String inputID) {
        Pattern pattern = Pattern.compile("^[0-9]{12}$");
        if (pattern.matcher(inputID).find()) {
            generateSubMenu(inputID);//Tạo giao diện con để chọn từ 1~3
            chooseSubMenu(inputID);
        } else {
            System.out.println("ID number is invalid, please try again right below here or press \"No\" to Exit");
            String input = sc.next();
            if (input.equals("No")) {
                whenClicked2OrExit();
            }
            checkInputID(input);
        }
    }

    public static void generateSubMenu(String inputID) {
        System.out.println("    | 1. Kiểm tra nơi sinh.");
        System.out.println("    | 2. Kiểm tra năm sinh, giới tính.");
        System.out.println("    | 3. Kiểm tra 6 số cuối CCCD.");
        System.out.println("    | 0. Thoát.");
        System.out.println("Select your option: ");
        chooseSubMenu(inputID);
    }
    public static void chooseSubMenu(String inputID) {
        try {
            int input = Integer.parseInt(sc.next());
            switch (input) {
                case 1:
                    System.out.println("Place of birth: " + checkPlaceOfBirth(inputID));
                    generateSubMenu(inputID);
                    break;
                case 2:
                    System.out.println("Birth year and gender: " + checkYearAndGender2(inputID));
                    generateSubMenu(inputID);
                    break;
                case 3:
                    System.out.println("6 số cuối căn cước công dân: " + inputID.substring(6));
                    generateSubMenu(inputID);
                    break;
                case 0:
                    whenClicked2OrExit();
                    break;
                default: {
                    System.out.println("Choose again: ");
                    generateSubMenu(inputID);
                }
            }
        }
        catch (InputMismatchException exception) {
            System.out.println("Ngoại lệ: Đầu vào không khớp");
            chooseSubMenu(inputID);
        }
        catch (NumberFormatException exception) {
            System.out.println("Ngoại lệ khi chọn submenu: Nhập số chứ không nhập chữ");
            chooseSubMenu(inputID);
        }
        catch (StackOverflowError exception) {
            System.out.println("Ngoại lệ: Tràn bộ nhớ");
            chooseSubMenu(inputID);
        }
    }

    private static void whenClicked2OrExit() {
        System.exit(0);
        System.out.println("Exit!!!");
        sc.close();
    }

    public static String checkPlaceOfBirth(String inputID) {
        System.out.println("Kiểm tra nơi sinh");
        String newString = inputID.substring(0, 3);
        String result = "".equals(placeBorn(newString)) ? "This province is not found" : placeBorn(newString);
//        System.out.println("Tên tỉnh: " + result);
        return result;
    }


    public static void initCenter(){
        int index = 0;
        for (int i = 19; i < 24; i++){
            if (index >= 10) break;
            List<String> list = new ArrayList<>();
            for (int j = 0; j< 2; j++){
                String value = String.valueOf(index);
                list.add(value);
                index++;
            }
            cemterMap.put(String.valueOf(i), list);
        }
    }
    public static String checkYearAndGender2(String inputID) {
        char subInputID = inputID.charAt(3);
        int num = Integer.parseInt(String.valueOf(subInputID));
        String gender = (num % 2 == 0) ? "Nam" : "Nữ";
        for (Map.Entry<String, List<String>> entry : cemterMap.entrySet()){
            if (entry.getValue().contains(String.valueOf(num))){
                return gender + " | "+ entry.getKey()+ inputID.substring(4,6);
            }
        }
       return "";
    }

    public static void checkYearAndGender(String inputID) {
        char subInputID = inputID.charAt(3);
        System.out.println("Kiểm tra năm sinh, giới tính.");
        switch (subInputID) {
            case '0':
                System.out.println("Nam |" + ("19" + inputID.substring(4, 6)));
                break;
            case '1':
                System.out.println("Nữ |" + ("19" + inputID.substring(4, 6)));
                break;
            case '2':
                System.out.println("Nam |" + ("20" + inputID.substring(4, 6)));
                break;
            case '3':
                System.out.println("Nữ |" + ("20" + inputID.substring(4, 6)));
                break;
            case '4':
                System.out.println("Nam |" + ("21" + inputID.substring(4, 6)));
                break;
            case '5':
                System.out.println("Nữ |" + ("21" + inputID.substring(4, 6)));
                break;
            case '6':
                System.out.println("Nam |" + ("22" + inputID.substring(4, 6)));
                break;
            case '7':
                System.out.println("Nữ |" + ("22" + inputID.substring(4, 6)));
                break;
            case '8':
                System.out.println("Nam |" + ("23" + inputID.substring(4, 6)));
                break;
            case '9':
                System.out.println("Nữ |" + ("23" + inputID.substring(4, 6)));
                break;
        }
    }

    public static String placeBorn(String threeNumber) {
        for (int i = 0; i < provinceCode.size(); i++) {
            if (threeNumber.equals(provinceCode.get(i))) {
                return getNameProvince(i);
            }
        }
        return "";
    }

    static String getNameProvince(int index) {
        for (int i = 0; i < provinceName.size(); i++) {
            if (i == index) {
                return provinceName.get(i);
            }
        }
        return "";
    }

    public static String generateRandomString() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}