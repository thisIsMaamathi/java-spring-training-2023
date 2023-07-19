package org.example;

import java.util.ArrayList;

public  class Bank implements BankOperations,CustomerOperations{
    private String bankNAme;
    private String ifsc;
    private int bankId;

    //generators---------------
    private static int bankIdGenerator=60;

    int cardNumberGenerator=2001;

    int pinGenerator=3001;

    int customerId = 1001;
    //------------------------------

    //lists--------
    protected ArrayList<Customer> customers;

   //------------------
    public String getBankNAme() {
        return bankNAme;
    }
    public String getIfsc() {
        return ifsc;
    }

    public Bank(){}


    public Bank(String bankNAme,String ifsc) {
        this.bankNAme=bankNAme;
        this.ifsc=ifsc;
        this.bankId=bankIdGenerator++;

        System.out.println(bankNAme+" "+ifsc+" "+bankId);
    }



    //Bank adds new customer to it by getting the informtion it needs
    @Override
    public void addNewCustomer(String name) {
        Customer customer=new Customer(this.customerId);
        customers.add(customer);
        customer.setName(name);
        System.out.println("Customer "+customer.getName()+" created succesfully and their customer id is "+customerId);
        customerId++;
    }
    @Override
    public void viewCustomerList() {
        for(Customer customer:customers){
            System.out.println("CustomerID : "+customer.getCustomerId()+"  CustomerName :  "
                    +customer.getName()+"  CIBILScore :  "
                    + customer.getCIBILScore()+"    | ");
        }
    }
    @Override
    public void viewCustomer(int customerId) {
        int customerFlag=0;
     for(Customer customer : customers){
         if(customer.getCustomerId()==customerId){
             customerFlag=1;
             System.out.println(customer.getCustomerId()+"   "
                     +customer.getName()+"    "
             + customer.getCIBILScore()+"    | ");
         }

     }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }
    @Override
    public void addNewCard(int customerId) {
        int customerFlag=0;
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                customerFlag=1;
                if (customer.getCardRequestFlag() == 1) {
                    Card card = new Card(customerId, cardNumberGenerator++, pinGenerator++,"GOLD");
                    customer.addtoCardNumbers(card);
                    customer.setCardRequestFlag(0);
                    System.out.println("Card number " + card.getCardNumber() + "    Card Pin: ****    "  + "Current Balance : "+card.getCardBalance());
                }
                else System.out.println("You have not applied for a card");
            }

        }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }


    @Override
    public void viewCards(int customerId) {
        int customerFlag=0;
        for(Customer customer: customers){
            customerFlag=1;
            if(customer.getCustomerId()==customerId){
                 for(Card card: customer.getCardNumbers()){
                     if(card.getCardStatus()==true)
                         System.out.println("CustomerID: "+card.getCustomerId()+"    | "+"Card number: "+card.getCardNumber()+"    |Card Balance: "+card.getCardBalance()+"  |CIBIL Score:"+card.getCIBILScore());
                 }
                 }
            }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");

        }

    @Override
    public void viewBlockedCards() {
        for (Customer customer : customers) {
                for (Card card : customer.getCardNumbers()) {
                  if(!card.getCardStatus())
                      System.out.println(card.getCustomerId()+"    | "+"Card number "+card.getCardNumber()+"    | "+card.getCardBalance()+"    | "+card.getCIBILScore());

                }
            }

        }


    @Override
    public void blockCard(int customerId, int cardNumber) {
        int customerFlag = 0;
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                customerFlag = 1;
                int cardFlag = 0;
                for (Card card : customer.getCardNumbers()) {
                    if (card.getCardNumber() == cardNumber) {
                        cardFlag = 1;
                        card.setCardStatus(false);
                        System.out.println("Card Blocked Successfully");
                    }
                }
                if (cardFlag == 0) System.out.println("Card not found");
            }

        } if (customerFlag == 0) System.out.println("Customer with this customer Id doesnot exsist");
    }

    @Override
    public void viewAllCards() {
        for(Customer customer:customers){
            for(Card card:customer.getCardNumbers()){
                if(card.getCardStatus())
                { System.out.println(card.getCustomerId()+"    | "+"Card number "+card.getCardNumber()+"    | "+card.getCardBalance()+"    | "+card.getCIBILScore());}
            }
        }

    }

    @Override
    public void applyCreditCard(int customerId) {
        int customerFlag=0;
        for (Customer customer : customers) {
            if (customerId == customer.getCustomerId()) {
                customerFlag=1;
                if (customer.getCardNumbersSize() > 5)
                    System.out.println("No of credit cards exceeded " + customer.getCardNumbersSize());
                else {
                    customer.setCardRequestFlag(1);
                    System.out.println("Request for your card is Accepted , No.of cards you own already :" + customer.getCardNumbersSize());
                }
            }
        }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }



    @Override
    public void deposit(int customerId,int cardNumber, int pin, int amount) {
        int customerFlag=0;
        for (Customer customer : customers) {
            customerFlag=1;
            if (customerId == customer.getCustomerId()) {
                ArrayList<Card> cardNumbers = customer.getCardNumbers();
                System.out.println(cardNumbers);
                int cardFlag=0;
                 for (Card card : cardNumbers) {
                    if (card.getCardNumber() == cardNumber) {
                        if (card.getPin() == pin) {
                            cardFlag=1;
                            float balance = card.getCardBalance();
                            balance += amount;
                            card.setCardBalance(balance);
                            System.out.println("the current balance after deposit   " + card.getCardBalance());
                        } else System.out.println("Pin mismatch");
                    }
                }if(cardFlag==0) System.out.println("Card not found");
            }
        }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }

    @Override
    public void withdraw(int customerId,int cardNumber, int pin, int amount) {
        int customerFlag=0;
        for (Customer customer : customers) {
            if (customerId == customer.getCustomerId()) {
                customerFlag=1;
                ArrayList<Card> cardNumbers = customer.getCardNumbers();
                 int cardFlag=0;
                for (Card card : cardNumbers) {
                    if (card.getCardNumber() == cardNumber) {
                        if (card.getPin() == pin) {
                            cardFlag=1;
                            float balance = card.getCardBalance();
                            balance -= amount;
                            card.setCardBalance(balance);
                            System.out.println("the current balance after deposit   " + card.getCardBalance());
                        } else System.out.println("Pin mismatch");
                    }
                }if(cardFlag==0) System.out.println("Card not found");
            }
        }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }

    @Override
    public void checkBalance(int cardNumber, int pin, int customerId) {
        int customerFlag=0;
        for (Customer customer : customers) {
            customerFlag=1;
            if (customerId == customer.getCustomerId()) {
                ArrayList<Card> cardNumbers = customer.getCardNumbers();
                int cardFlag=0;
                for (Card card : cardNumbers) {
                    if (card.getCardNumber() == cardNumber) {
                        cardFlag=1;
                        if (card.getPin() == pin) {
                            System.out.println("the current balance is " + card.getCardBalance());
                        } else System.out.println("Pin mismatch");
                    }
                }if(cardFlag==0) System.out.println("Card not found");
            }
        }if(customerFlag==0) System.out.println("Customer with this customer Id doesnot exsist");
    }
}
