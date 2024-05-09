package com.bankSystem.entity;

import com.bankSystem.exception.InsufficientFundsException;

public abstract class Transaction {
    protected double amount;
    protected Account originatingAccount;
    protected Account resultingAccount;
    protected String transactionReason;

    public Transaction(double amount, Account originatingAccount, Account resultingAccount, String transactionReason) {
        this.amount = amount;
        this.originatingAccount = originatingAccount;
        this.resultingAccount = resultingAccount;
        this.transactionReason = transactionReason;
    }

    public abstract void execute() throws InsufficientFundsException;

    public Account getOriginatingAccount() {
        return originatingAccount;
    }

	public double getAmount() {
		
		return amount;
	}
}
