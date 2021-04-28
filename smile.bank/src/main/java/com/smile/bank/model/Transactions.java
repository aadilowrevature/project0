package com.smile.bank.model;

public class Transactions {

    private int customer_id;
    private int acc_num;
    private String account_type;
    private String transaction_type;
    private double amount;
    private int transaction_num;
    private String timestamp;
    private String name;

    public Transactions() {
        // TODO Auto-generated constructor stub
    }

    public Transactions(String name, int customer_id, int acc_num, String account_type, String transaction_type, double amount, int transaction_num, String timestamp) {
        super();
        this.customer_id = customer_id;
        this.acc_num = acc_num;
        this.account_type = account_type;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.transaction_num = transaction_num;
        this.timestamp = timestamp;
        this.name=name;
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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTransaction_num() {
        return transaction_num;
    }

    public void setTransaction_num(int transaction_num) {
        this.transaction_num = transaction_num;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Transactions{");

        sb.append(", name='").append(name);

        sb.append(", acc_num=").append(acc_num);
        sb.append(", account_type='").append(account_type).append('\'');
        sb.append(", transaction_type='").append(transaction_type).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", transaction_num=").append(transaction_num);
        sb.append(", timestamp='").append(timestamp).append('\'');


        sb.append("customer_id=").append(customer_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

