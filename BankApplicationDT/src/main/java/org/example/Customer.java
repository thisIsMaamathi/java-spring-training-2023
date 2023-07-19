package org.example;

import java.util.ArrayList;

public class Customer {


    private final int customerId;
    private String name;
    private int cardRequestFlag = 0;
    private int CIBILScore = 0;
    private ArrayList<Card> cardNumbers;

    //==================================
    public Customer(int customerId) {
        //autogenerate customer id
        this.customerId = customerId;
        cardNumbers = new ArrayList<Card>(5);
    }

    //getter-setter-------
    public int getCardRequestFlag() {
        return cardRequestFlag;
    }

    public void setCardRequestFlag(int cardRequestFlag) {
        this.cardRequestFlag = cardRequestFlag;
    }

    public int getCardNumbersSize() {
        return this.cardNumbers.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCIBILScore() {
        return CIBILScore;
    }

    public void setCIBILScore(int CIBILScore) {
        this.CIBILScore = CIBILScore;
    }

    public ArrayList<Card> getCardNumbers() {
        return cardNumbers;
    }

    public void setCardNumbers(ArrayList<Card> cardNumbers) {
        this.cardNumbers = cardNumbers;
    }

    public void addtoCardNumbers(Card card) {
        this.cardNumbers.add(card);
    }

    public int getCustomerId() {
        return customerId;
    }


}


