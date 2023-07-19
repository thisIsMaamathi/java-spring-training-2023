package org.example;

public interface BankOperations {


    void addNewCustomer(String name);

    void viewCustomerList();

    void viewCustomer(int customerId);

    void addNewCard(int customerId);

    void viewCards(int customerId);

    void viewBlockedCards();

    void blockCard(int cardNumber, int customerId);

    void viewAllCards();

}
