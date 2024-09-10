package vn.funix.FX22520.java.asm04.enums;

public enum MenuType {
    VIEW_CUSTOMERS(1),
    ADD_CUSTOMERS(2),
    ADD_SAVINGS_ACCOUNT(3),
    WITHDRAW(5),
    TRANSFER(4),
    HISTORY(6),
    EXIT(0);
    private final int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuType getInputMenu(int value){
        MenuType[] values = values();
        for (MenuType menuType : values){
            if (menuType.getValue() == value) return menuType;
        }
        return null;
    }

}
