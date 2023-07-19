package org.example;

import java.util.Scanner;

public class Main {
    public static ABCBank abcBank = ABCBank.getInstance();
    public static XYZBank xyzBank = XYZBank.getInstance();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, adminChoice, custChoice, bankChoice;
        Bank bankObj = new Bank();

        while (true) {
            System.out.println("Choose your bank  1.ABCBank  2.XYZBank  3.Exit");
            bankChoice = sc.nextInt();
            if (bankChoice == 1) {
                System.out.println("Welcome to ABC Bank");
                bankObj = abcBank;
            } else if (bankChoice == 2) {
                bankObj = xyzBank;
                System.out.println("Welcome to XYZ Bank");
            } else System.exit(0);

            while (true) {
                System.out.println("Choose your role  1.BankAdmin  2.Customer  3.Exit");
                choice = sc.nextInt();
                if (choice == 1) {

                    while (true) {
                        System.out.println("Enter choices 1.Add customer  2.Add Card  3.Block Card  4.View Cards  5.View all customer data  6.View all cards 7.ViewAll blocked cards  8.Exit");
                        adminChoice = sc.nextInt();
                        if (adminChoice == 8) break;
                        switch (adminChoice) {
                            case 1 -> {
                                String name;
                                System.out.println("Enter CustomerName....");
                                name = sc.next();
                                bankObj.addNewCustomer(name);
                            }
                            case 2 -> {
                                int customerId;
                                System.out.println("Enter customerId");
                                customerId = sc.nextInt();
                                bankObj.addNewCard(customerId);
                            }
                            case 3 -> {
                                int customerId, cardNumber;
                                System.out.println("Enter customerID");
                                customerId = sc.nextInt();
                                System.out.println("Enter Card Number");
                                cardNumber = sc.nextInt();
                                bankObj.blockCard(customerId, cardNumber);
                            }
                            case 4 -> {
                                int customerId;
                                System.out.println("Enter your customerID");
                                customerId = sc.nextInt();
                                bankObj.viewCards(customerId);
                            }
                            case 5 -> {
                                bankObj.viewCustomerList();
                            }
                            case 6 -> {
                                bankObj.viewAllCards();

                            }
                            case 7 -> {
                                bankObj.viewBlockedCards();
                            }


                        }
                    }

                }
                if (choice == 2) {

                    while (true) {

                        System.out.println("Enter Choices  1.Request Credit card  2.View Balance  3.Block Card  4.Deposit  5.Withdraw 6.Purchase  7.Exit");
                        custChoice = sc.nextInt();
                        if (custChoice == 7) break;
                        switch (custChoice) {

                            case 1 -> {int customerId;
                                System.out.println("Enter CustomerId");
                                customerId=sc.nextInt();
                                bankObj.applyCreditCard(customerId);
                            }
                            case 2 -> {
                                int cardNumber,pin=0,customerId;
                                System.out.println("Enter cardNUmber");  cardNumber=sc.nextInt();
                                System.out.println("Enter pin"); pin=sc.nextInt();
                                System.out.println("Enter customerId"); customerId=sc.nextInt();
                                bankObj.checkBalance(cardNumber,pin,customerId);
                            }
                            case 3 -> {
                                int cardNumber,customerId;
                                System.out.println("Enter customerId");  customerId=sc.nextInt();
                                System.out.println("Enter cardNumber"); cardNumber=sc.nextInt();

                                bankObj.blockCard(customerId,cardNumber);
                            }
                            case 4 -> {
                                int cardNumber,pin=0,customerId,amount;
                                System.out.println("Enter cardNUmber");  cardNumber=sc.nextInt();
                                System.out.println("Enter pin"); pin=sc.nextInt();
                                System.out.println("Enter customerId"); customerId=sc.nextInt();
                                System.out.println("Enter Amount");  amount=sc.nextInt();
                                bankObj.deposit(customerId, cardNumber,pin,amount);
                            }
                            case 5 -> {
                                int cardNumber,pin=0,customerId,amount;
                                System.out.println("Enter cardNUmber");  cardNumber=sc.nextInt();
                                System.out.println("Enter pin"); pin=sc.nextInt();
                                System.out.println("Enter customerId"); customerId=sc.nextInt();
                                System.out.println("Enter Amount");  amount=sc.nextInt();
                                bankObj.withdraw(customerId,cardNumber,pin,amount);
                            }
                            case 6 -> {
                                int item = 0;
                                System.out.println("to purchase  1. Apple--> Rs100  2.WaterMelon-->200");
                                item = sc.nextInt();
                                int cardNumber,pin=0,customerId;
                                System.out.println("Enter cardNUmber");  cardNumber=sc.nextInt();
                                System.out.println("Enter pin"); pin=sc.nextInt();
                                System.out.println("Enter customerId"); customerId=sc.nextInt();

                                if (item == 1) bankObj.withdraw(customerId,cardNumber,pin,100);
                                else bankObj.withdraw(customerId,cardNumber,pin,200);
                            }


                        }
                    }
                }
                if (choice == 3) {
                    break;
                }
            }

        }


    }
}