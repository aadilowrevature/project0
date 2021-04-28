package com.smile.bank.model;

public class Account {

    private double balance;
    private int customer_id;
    private int acc_num;
    private String account_status;
    private String account_type;

    public Account() {
    }

    public Account(double balance, int customer_id, int acc_num, String account_status, String account_type) {
        super();
        this.balance = balance;
        this.customer_id = customer_id;
        this.acc_num = acc_num;
        this.account_status = account_status;
        this.account_type = account_type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(int acc_num) {
        this.acc_num = acc_num;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", customer_id=" + customer_id +
                ", acc_num=" + acc_num +
                ", account_status='" + account_status + '\'' +
                ", account_type='" + account_type + '\'' +
                '}';
    }
}
