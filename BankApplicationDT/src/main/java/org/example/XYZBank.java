package org.example;

import java.util.ArrayList;

public class XYZBank extends Bank {
    private static final XYZBank xyzBank=new XYZBank();

    //Constructor initializes bank details and creates a customer list
    private XYZBank() {

        super("XYZ Bank", "100002");
        System.out.println("Welcome to XYZ Bank....");
        customers = new ArrayList<>();
        System.out.println("customer list created");

    }
    public static XYZBank getInstance(){
        return xyzBank;
    }


}
