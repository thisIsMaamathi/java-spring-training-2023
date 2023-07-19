package org.example;

import java.util.ArrayList;

public class ABCBank extends Bank {
    private static final ABCBank abcBank=new ABCBank();
    private ABCBank() {
        super("ABC Bank", "100003");
        System.out.println("Welcome to ABC Bank....");
        customers = new ArrayList<>();
        System.out.println("customer list created");
    }
    public static ABCBank getInstance(){
       return abcBank;
    }

}
