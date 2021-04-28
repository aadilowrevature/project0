package com.smile.bank.model;

public class Savings {
        private double balance;
        private int customer_id;
        private int acc_num;
        private String account_status;

        public Savings() {

        }

        public Savings(double balance, int customer_id, int acc_num, String account_status) {
            super();
            this.balance = balance;
            this.customer_id = customer_id;
            this.acc_num = acc_num;
            this.account_status = account_status;
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

        @Override
        public String toString() {
            return "Savings{" +
                    "balance=" + balance +
                    ", customer_id=" + customer_id +
                    ", acc_num=" + acc_num +
                    ", account_status='" + account_status + '\'' +
                    '}';
        }
}
