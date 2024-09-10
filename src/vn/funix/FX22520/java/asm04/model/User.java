package vn.funix.FX22520.java.asm04.model;

import java.io.Serial;
import java.io.Serializable;

public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String customerId;
    public User(String name, String customerId) {
            this.name = name;
            this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

//    public boolean isValidId(String idCardNumber) {
//
//        return false;
//    }
}