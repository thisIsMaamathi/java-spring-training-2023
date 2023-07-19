package org.example;

public interface CustomerOperations {

    void applyCreditCard(int customerId);

    void blockCard(int cardNumber, int customerId);

    void deposit(int customerId, int cardNumber, int pin, int amount);

    void withdraw(int customerId, int cardNumber, int pin, int amount);

    void checkBalance(int cardNumber, int pin, int customerId);


}
