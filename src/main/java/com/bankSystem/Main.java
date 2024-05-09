package com.bankSystem;

import com.bankSystem.entity.Bank;
import com.bankSystem.entity.Account;
import com.bankSystem.entity.DepositTransaction;
import com.bankSystem.entity.WithdrawalTransaction;
import com.bankSystem.exception.AccountNotFoundException;
import com.bankSystem.exception.InsufficientFundsException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank("Raiffeisen Bank");

        while (true) {
            System.out.println("Welcome to the Raiffeisen Bank. Choose an option:");
            System.out.println("1. Create an account");
            System.out.println("2. Make a deposit");
            System.out.println("3. Make a withdrawal");
            System.out.println("4. Check account balance");
            System.out.println("5. List all accounts");
            System.out.println("6. Check total transaction fees");
            System.out.println("7. Check total withdrawn amount");  
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter account ID:");
                    int id = scanner.nextInt();
                    System.out.println("Enter user name:");
                    String name = scanner.next();
                    System.out.println("Initial deposit amount:");
                    double initialAmount = scanner.nextDouble();
                    Account newAccount = new Account(id, name, initialAmount);
                    bank.addAccount(newAccount);
                    System.out.println("Account created successfully for " + name + " with ID " + id + " and an initial deposit of $" + String.format("%.2f", initialAmount));
                    break;
                case 2:
                    System.out.println("Enter account ID:");
                    int depositId = scanner.nextInt();
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    try {
                        Account depositAccount = bank.getAccount(depositId);
                        DepositTransaction deposit = new DepositTransaction(depositAmount, depositAccount, "Deposit");
                        bank.executeTransaction(deposit, false);
                        System.out.println(depositAccount.getUserName() + " (Account ID: " + depositId + ") deposited $" + String.format("%.2f", depositAmount) + ". New balance: $" + String.format("%.2f", depositAccount.getBalance()));
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Enter account ID:");
                    int withdrawalId = scanner.nextInt();
                    System.out.println("Enter amount to withdraw:");
                    double withdrawalAmount = scanner.nextDouble();
                    try {
                        Account withdrawalAccount = bank.getAccount(withdrawalId);
                        WithdrawalTransaction withdrawal = new WithdrawalTransaction(withdrawalAmount, withdrawalAccount, "Withdrawal");
                        bank.executeTransaction(withdrawal, true);
                        System.out.println(withdrawalAccount.getUserName() + " (Account ID: " + withdrawalId + ") withdrew $" + String.format("%.2f", withdrawalAmount) + ". Remaining balance: $" + String.format("%.2f", withdrawalAccount.getBalance()));
                    } catch (AccountNotFoundException | InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter account ID:");
                    int balanceId = scanner.nextInt();
                    try {
                        Account balanceAccount = bank.getAccount(balanceId);
                        System.out.println(balanceAccount.getUserName() + " (Account ID: " + balanceId + ") has a balance of $" + String.format("%.2f", balanceAccount.getBalance()));
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    bank.listAccounts();
                    break;
                case 6:
                    System.out.println("Total transaction fees collected: $" + String.format("%.2f", bank.getTotalTransactionFees()));
                    break;
                case 7:
                    System.out.println("Total withdrawn amount: $" + String.format("%.2f", bank.getTotalWithdrawnAmount()));
                    break;
                case 8:
                    System.out.println("Thank you for using our bank!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
