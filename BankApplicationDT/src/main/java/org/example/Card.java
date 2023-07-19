package org.example;


public class Card extends Customer {
    private int customerID;
    private int cardNumber;
    private String cardType;
    private Boolean cardStatus = true;
    private float cardBalance;

    private String CardType;
    private int pin;

    //card number is generated during the time of initialization
    Card(int customerId, int cardNumber, int pin, String cardType) {
        super(customerId);
        this.cardNumber = cardNumber;
        this.cardType = cardType;

        if (cardType.equalsIgnoreCase("gold")) this.cardBalance = 1000;
        else if (cardType.equalsIgnoreCase("diamond")) this.cardBalance = 10000;
        else this.cardBalance = 100000;

        this.cardStatus = true;
        this.pin = pin;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Card{" +
                "customerID=" + customerID +
                ", cardNumber=" + cardNumber +
                ", cardType='" + cardType + '\'' +
                ", cardStatus=" + cardStatus +
                ", cardBalance=" + cardBalance +
                ", pin=" + pin +
                '}';
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Boolean getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Boolean cardStatus) {
        this.cardStatus = cardStatus;
    }

    public float getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(float cardBalance) {
        this.cardBalance = cardBalance;
    }


}