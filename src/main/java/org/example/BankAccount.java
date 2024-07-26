package org.example;

import java.util.Scanner;

public class BankAccount {
    private double balance;
    private String accountHolderName;
    private String accountNumber;

    // Constructor with all parameters
    public BankAccount(double balance, String accountHolderName, String accountNumber) {
        this.balance = balance;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
    }

    // Constructor without account number (generates a unique one)
    public BankAccount(double balance, String accountHolderName) {
        this(balance, accountHolderName, generateUniqueAccountNumber());
    }

    // Parameterless constructor for user input
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account holder's name: ");
        this.accountHolderName = scanner.nextLine();

        System.out.print("Enter starting balance: ");
        this.balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        // Generate a unique account number
        this.accountNumber = generateUniqueAccountNumber();
    }

    private static String generateUniqueAccountNumber() {
        // Generate a simple unique account number based on current time
        return "ACC" + System.currentTimeMillis();
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Getter for account holder name
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Getter for account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to print account details
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\nAccount Holder: " + accountHolderName + "\nBalance: $" + balance;
    }
}
