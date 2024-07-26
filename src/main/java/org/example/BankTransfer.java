package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankTransfer {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample accounts for testing
        accounts.add(new BankAccount(4400.0, "Hector"));
        accounts.add(new BankAccount(3600.0, "Matt"));

        System.out.println("Hello World! Welcome to the Bank of Matt!");

        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. yes");
            System.out.println("2. no");
            int choice = getValidIntInput(scanner);

            if (choice == -1) {
                break;
            } else if (choice == 1) {
                handleExistingCustomer(scanner);
            } else if (choice == 2) {
                handleNewAccount(scanner);
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or -1 to exit.");
            }
        }
        scanner.close();
    }

    private static void handleExistingCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        BankAccount existingAccount = null;
        for (BankAccount account : accounts) {
            if (account.getAccountHolderName().equalsIgnoreCase(name)) {
                existingAccount = account;
                break;
            }
        }
        if (existingAccount != null) {
            mainMenu(existingAccount, scanner);
        } else {
            System.out.println("No account found for the name: " + name);
        }
    }

    private static void handleNewAccount(Scanner scanner) {
        System.out.println("Let's make a new account!");
        System.out.print("What is the name for the account? ");
        String name = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        double balance = getValidDoubleInput(scanner);
        BankAccount newAccount = new BankAccount(balance, name);
        accounts.add(newAccount);
        System.out.println("New account created successfully!");
        System.out.println(newAccount);
        mainMenu(newAccount, scanner);
    }

    private static void mainMenu(BankAccount account, Scanner scanner) {
        while (true) {
            System.out.println("Hello " + account.getAccountHolderName() + "! Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit");
            System.out.print("Please enter your choice: ");

            int choice = getValidIntInput(scanner);

            switch (choice) {
                case 0:
                    System.out.println("Exiting to main menu.");
                    return;
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = getValidDoubleInput(scanner);
                    account.withdraw(withdrawAmount);
                    System.out.println("Updated account details:");
                    System.out.println(account);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = getValidDoubleInput(scanner);
                    account.deposit(depositAmount);
                    System.out.println("Updated account details:");
                    System.out.println(account);
                    break;
                case 4:
                    System.out.print("Please enter the account number to transfer to: ");
                    String recipientAccountNumber = scanner.nextLine();
                    BankAccount recipientAccount = null;
                    for (BankAccount acc : accounts) {
                        if (acc.getAccountNumber().equals(recipientAccountNumber)) {
                            recipientAccount = acc;
                            break;
                        }
                    }
                    if (recipientAccount != null) {
                        System.out.print("Please enter the amount to transfer: ");
                        double transferAmount = getValidDoubleInput(scanner);
                        transfer(account, recipientAccount, transferAmount);
                        System.out.println("Updated your account details:");
                        System.out.println(account);
                        System.out.println("Updated recipient's account details:");
                        System.out.println(recipientAccount);
                    } else {
                        System.out.println("Account doesn't exist.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, 2, 3, or 4.");
                    break;
            }
        }
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private static void transfer(BankAccount fromAccount, BankAccount toAccount, double amount) {
        if (amount > 0 && fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed. Check the amount and balance.");
        }
    }
}
