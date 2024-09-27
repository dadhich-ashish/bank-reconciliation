package com.tools.bank.model;

import java.util.Date;

public class BankStatement {

    private Date transactionDate;
    private String chequeNumber;
    private double withdrawal;
    private double deposit;
    private double balance;
    private String narration;
    private boolean found;


    public BankStatement(Date transactionDate, String chequeNumber, double withdrawal, double deposit, double balance,
            String narration) {
        this.transactionDate = transactionDate;
        this.chequeNumber = chequeNumber;
        this.withdrawal = withdrawal;
        this.deposit = deposit;
        this.balance = balance;
        this.narration = narration;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }


    @Override
    public String toString() {
        return "BankStatement{" +
                "transactionDate='" + transactionDate + '\'' +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", withdrawal=" + withdrawal +
                ", deposit=" + deposit +
                ", balance=" + balance +
                ", narration='" + narration + '\'' +
                '}';
    }
}