package com.bankSystem.entity;

import java.util.ArrayList;
import java.util.List;

import com.bankSystem.exception.AccountNotFoundException;
import com.bankSystem.exception.InsufficientFundsException;

public class Bank {
    private String name;
    private List<Account> accounts = new ArrayList<>();
    private double totalTransactionFees = 0.0;
    private double totalTransferAmount = 0.0;  
    private double totalWithdrawnAmount = 0.0; 
    private double transactionFlatFee = 10.0;  
    private double transactionPercentFee = 0.05; 

    public Bank(String name) {
        this.setName(name);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added: " + account.getUserName());
    }

    public Account getAccount(int accountId) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
    }

    public void executeTransaction(Transaction transaction, boolean isFlatFee) throws InsufficientFundsException {
        transaction.execute();
        if (transaction instanceof WithdrawalTransaction) {
            totalWithdrawnAmount += transaction.getAmount(); 
        }
        applyTransactionFee(transaction.getOriginatingAccount(), isFlatFee);
    }

    private void applyTransactionFee(Account account, boolean isFlatFee) throws InsufficientFundsException {
        double fee = isFlatFee ? transactionFlatFee : account.getBalance() * transactionPercentFee;
        if (account.getBalance() >= fee) {
            account.withdraw(fee);
            totalTransactionFees += fee;
            System.out.println("Transaction fee of $" + fee + " applied.");
        } else {
            throw new InsufficientFundsException("Not enough funds to cover the transaction fee.");
        }
    }

    public void recordTransfer(double amount) {
        totalTransferAmount += amount;
        System.out.println("Transfer recorded: $" + amount);
    }

    public double getTotalTransactionFees() {
        return totalTransactionFees;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public double getTotalWithdrawnAmount() {
        return totalWithdrawnAmount;
    }

    public void listAccounts() {
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountId() + ", Name: " + account.getUserName() + ", Balance: $" + account.getBalance());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
