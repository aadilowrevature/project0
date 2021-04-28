package com.smile.bank.functions;

import com.smile.bank.consoles.OpenAccountConsole;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.service.*;
import com.smile.bank.functions.service.impl.*;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Account;
import com.smile.bank.model.Transactions;
import com.smile.bank.model.OpenAccount;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Functions {
    //bulk of the methods
    SmileLog smile = new SmileLog();
    Scanner scanner = new Scanner(System.in);
    OpenAccountService OAS = new OpenAccountServiceImpl();
    ApproveAccountService AAS = new ApproveAccountServiceImpl();
    ViewAccountService VAS = new ViewAccountServiceImpl();
    NewTransactionService NTC = new NewTransactionServiceImpl();
    QuickFindService find = new QuickFindServiceImpl();
    Validator valid = new Validator();

    // *************Customer Methods********************
    public void OpenAccountMethod(String email) {

        OpenAccountConsole open = new OpenAccountConsole();

        open.openAccConsole(email);

    }

    public void OpenCheckingMethod(String email) {
        OpenAccount newAccount = new OpenAccount();
        double amount = 0;
        try {
            smile.message("Please Enter a Starting Balance for Checking: ");
            amount = Double.parseDouble(scanner.nextLine());
            newAccount.setBalance(amount);

        } catch (NumberFormatException e) {
            smile.message("Your entry is invalid!");
        }
        try {
            newAccount.setCustomer_id(find.findID(email));
        } catch (SmileException e) {
            e.printStackTrace();
        }

        try {
            if (OAS.openChecking(newAccount) == 1) {
                smile.message("Your new checking account is pending!");
                smile.message("Starting balance $" + newAccount.getBalance());
                smile.message("");
            }
        } catch (SmileException e1) {
            smile.warn("Checking Account Creation failed");
            smile.eventFail(e1);

        }
    }

    public void OpenSavingsMethod(String email) {
        OpenAccount newAccount = new OpenAccount();
        double amount = 0;
        try {
            smile.message("Please Enter a Starting Balance for Savings: ");
            amount = Double.parseDouble(scanner.nextLine());
            newAccount.setBalance(amount);

        } catch (NumberFormatException e) {
            smile.message("Your entry is invalid!");
            smile.eventFail(e);
        }
        try {
            newAccount.setCustomer_id(find.findID(email));
        } catch (SmileException e) {
            e.printStackTrace();
        }

        try {
            if (OAS.openSavings(newAccount) == 1) {
                smile.message("Your new Savings Account is Pending!");
                smile.message("Starting balance $" + newAccount.getBalance());
                smile.message("");
            }
        } catch (SmileException e1) {
            smile.warn("Savings Account Creation failed");
            smile.eventFail(e1);

        }
    }


    public void WithdrawMethod(int ID) {
        int customer_id = 0;
        int acc_num = 0;
        String account_type = null;
        double balance = 0;
        double amount = 0;
        String entry = null;

        int ch = 0;

        do {
            try {
                smile.message("");
                smile.message("Will you be Withdrawing from Your Checking or Savings Account?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Exit");
                smile.message("");
                ch = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
            }

            int ch2 = 0;
            switch (ch) {
                case 1:
                case 2:
                    if (ch == 1) {
                        account_type = "checking";
                    }
                    if (ch == 2) {
                        account_type = "savings";
                    }

                    try {
                        List<Account> accounts = find.findAccounts(account_type, ID);
                        if (accounts != null) {
                            int val = 0;
                            do {
                                smile.message("Please select an account. You cannot withdraw from pending accounts");
                                int z = 0;
                                val = accounts.size();
                                for (int i = 0; i < val; i++) {

                                    smile.message((i + 1) + ") " + accounts.get(i));
                                }
                                smile.message("0) Cancel");

                                try {
                                    ch2 = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    ch2 = -1; //soft error handling. causes menu to reprint
                                }

                                if (ch2 > val || ch2 < 0) {
                                    smile.message("Invalid Entry");
                                } else if (ch2 == 0) {
                                    ch = 3;
                                    break;
                                } else {
                                    if (accounts.get(ch2 - 1).getAccount_status().equals("Pending")) {
                                        smile.message("Sorry, no transactions for Pending Accounts");
                                        ch2 = -1;
                                    } else {
                                        acc_num = accounts.get(ch2 - 1).getAcc_num();
                                        balance = accounts.get(ch2 - 1).getBalance();

                                        smile.message("How much would you like to withdraw? ");
                                        try {
                                            entry = scanner.nextLine();
                                            amount = Double.parseDouble(entry);
                                        } catch (NumberFormatException e) {
                                            smile.message("Sorry " + entry + " is not an acceptable input");
                                            amount = 0;
                                        }
                                        try {
                                            NTC.withdrawAcc(ID, acc_num, account_type, balance, amount);
                                        } catch (SmileException e) {
                                            // TODO Auto-generated catch block
                                            smile.message(e.getMessage());
                                        }
                                    }
                                }
                            } while (ch2 > val || ch2 < 0);


                        } else {
                            smile.message("Sorry, you do not have any Approved or Pending Checking Accounts.");
                        }


                    } catch (SmileException e) {
                        e.printStackTrace();
                    }

                    ch = 0;
                    break;
                case 3:
                    smile.message("Going Back...");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }

        } while (ch != 3);

    }

    public void DepositMethod(int ID) { //example of where i could have simplified code and not copy pasted redundantly
        int customer_id = 0;
        int acc_num = 0;
        String account_type = null;
        double amount = 0;
        String entry = null;

        int ch = 0;

        do {
            try {
                smile.message("");
                smile.message("Will you be Depositing to Your Checking or Savings Account?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Exit");
                smile.message("");
                ch = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
            }

            int ch2 = 0;
            switch (ch) {
                case 1:
                case 2:
                    if (ch == 1) {
                        account_type = "checking";
                    }
                    if (ch == 2) {
                        account_type = "savings";
                    }

                    try {
                        List<Account> accounts = find.findAccounts(account_type, ID);
                        if (accounts != null) {
                            int val = 0;
                            do {
                                smile.message("Please select an account. You cannot deposit to pending accounts");
                                int z = 0;
                                val = accounts.size();
                                for (int i = 0; i < val; i++) {

                                    smile.message((i + 1) + ") " + accounts.get(i));
                                }
                                smile.message("0) Cancel");

                                try {
                                    ch2 = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    ch2 = -1; //soft error handling. causes menu to reprint
                                }

                                if (ch2 > val || ch2 < 0) {
                                    smile.message("Invalid Entry");
                                } else if (ch2 == 0) {
                                    ch = 3;
                                    break;
                                } else {
                                    if (accounts.get(ch2 - 1).getAccount_status().equals("Pending")) {
                                        smile.message("Sorry, no transactions for Pending Accounts");
                                        ch2 = -1;
                                    } else {
                                        acc_num = accounts.get(ch2 - 1).getAcc_num();


                                        smile.message("How much would you like to Deposit? ");
                                        try {
                                            entry = scanner.nextLine();
                                            amount = Double.parseDouble(entry);
                                        } catch (NumberFormatException e) {
                                            smile.message("Sorry " + entry + " is not an acceptable input");
                                            amount = 0;
                                        }

                                        try {
                                            NTC.depositAcc(ID, acc_num, account_type, amount);
                                        } catch (SmileException e) {
                                            // TODO Auto-generated catch block
                                            smile.message(e.getMessage());
                                        }

                                    }
                                }
                            } while (ch2 > val || ch2 < 0);


                        } else {
                            smile.message("Sorry, you do not have any Approved or Pending Checking Accounts.");
                        }


                    } catch (SmileException e) {
                        e.printStackTrace();
                    }

                    ch = 0;
                    break;
                case 3:
                    smile.message("Going Back...");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }

        } while (ch != 3);


    }


    public void SendMoneyMethod(int ID) {
        int customer_id = 0;
        int acc_num = 0;
        String account_type = null;
        double balance = 0;
        double amount = 0;

        int target_customer_id = 0;
        int target_acc_num = 0;
        String target_account_type = null;

        String entry = null;

        int ch = 0;

        do {
            try {
                smile.message("");
                smile.message("Will you be Sending from Your Checking or Savings Account?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Exit");
                smile.message("");
                ch = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {
            }

            int ch2 = 0;
            switch (ch) {
                case 1:
                case 2:
                    if (ch == 1) {
                        account_type = "checking";
                    }
                    if (ch == 2) {
                        account_type = "savings";
                    }

                    try {
                        List<Account> accounts = find.findAccounts(account_type, ID);
                        if (accounts != null) {
                            int val = 0;
                            do {
                                smile.message("Please select an account. You cannot Send from pending accounts");
                                int z = 0;
                                val = accounts.size();
                                for (int i = 0; i < val; i++) {

                                    smile.message((i + 1) + ") " + accounts.get(i));
                                }
                                smile.message("0) Cancel");

                                try {
                                    ch2 = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException e) {
                                    ch2 = -1; //soft error handling. causes menu to reprint
                                }

                                if (ch2 > val || ch2 < 0) {
                                    smile.message("Invalid Entry");
                                } else if (ch2 == 0) {
                                    ch = 3;
                                    break;
                                } else {
                                    if (accounts.get(ch2 - 1).getAccount_status().equals("Pending")) {
                                        smile.message("Sorry, no transactions for Pending Accounts");
                                        ch2 = -1;
                                    } else {
                                        acc_num = accounts.get(ch2 - 1).getAcc_num();
                                        balance = accounts.get(ch2 - 1).getBalance();


                                        smile.message("How much would you like to Send? ");
                                        try {
                                            entry = scanner.nextLine();
                                            amount = Double.parseDouble(entry);
                                        } catch (NumberFormatException e) {
                                            smile.message("Sorry " + entry + " is not an acceptable input");
                                            amount = 0;
                                        }
                                        int ch3 = 0;
                                        smile.message("What is the receiving account type?");
                                        smile.message("1) Checking");
                                        smile.message("2) Savings");
                                        try {
                                            ch3 = Integer.parseInt(scanner.nextLine());
                                            if (ch3 == 1) {
                                                target_account_type = "checking";
                                            } else if (ch3 == 2) {
                                                target_account_type = "savings";
                                            } else {
                                                smile.message("Invalid selection");
                                            }
                                        } catch (NumberFormatException e) {
                                            ch3 = -1; //soft error handling. causes menu to reprint
                                        }


                                        smile.message("Please enter a target Account Number ");
                                        try {
                                            entry = scanner.nextLine();
                                            target_acc_num = Integer.parseInt(entry);
                                        } catch (NumberFormatException e) {
                                            smile.message("Sorry " + entry + " is not an acceptable input");
                                            amount = 0;
                                        }

                                        try {
                                            target_customer_id = find.findID(target_acc_num, target_account_type);

                                            NTC.sendMoney(target_customer_id, target_acc_num, target_account_type,
                                                    ID, acc_num, account_type, balance, amount);
                                        } catch (SmileException e) {
                                            // TODO Auto-generated catch block
                                            smile.message(e.getMessage());
                                        }
                                    }
                                }
                            } while (ch2 > val || ch2 < 0);


                        } else {
                            smile.message("Sorry, you do not have any Approved or Pending Checking Accounts.");
                        }


                    } catch (SmileException e) {
                        e.printStackTrace();
                    }

                    ch = 0;
                    break;
                case 3:
                    smile.message("Going Back...");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }

        } while (ch != 3);

    }

    public void ViewAccountCustomer(int ID) {
        String account_type = null;
        int ch = 0;
        int acc_num = 0;

        do {
            try {
                smile.message("");
                smile.message("Will you be Viewing Your Checking or Savings Account?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Exit");
                ch = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (ch) {
                case 1:
                    try {
                        smile.message("");
                        smile.message("Please enter an account number to view it");
                        acc_num = Integer.parseInt(scanner.nextLine());
                        smile.message("");
                    } catch (NumberFormatException e) {
                    }

                    account_type = "checking";
                    try {
                        Account account = find.findAccounts(account_type, ID, acc_num);
                        if (account != null) {
                            smile.message("" + account);
                        }
                    } catch (SmileException e) {
                        smile.message(e.getMessage());
                    }

                    ch = 0;
                    break;
                case 2:
                    try {
                        smile.message("");
                        smile.message("Please enter an account number to view it");
                        acc_num = Integer.parseInt(scanner.nextLine());
                        smile.message("");
                    } catch (NumberFormatException e) {
                    }

                    account_type = "savings";
                    try {
                        Account account = find.findAccounts(account_type, ID, acc_num);
                        if (account != null) {
                            smile.message("" + account);
                        }
                    } catch (SmileException e) {
                        smile.message(e.getMessage());
                    }

                    ch = 0;
                    break;
                case 3:
                    smile.message("Going Back...");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }

        } while (ch != 3);
    }

    // ****************************Employee Methods*****************************************************
    public void AccountApprovalMethod() {
        String account_type = null;
        int ch = 0;
        do {
            try {

                smile.message("Will you be Approving/Denying Checking or Savings?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Exit");
                ch = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (ch) {
                case 1:
                    if (ch == 1)
                        account_type = "checking";
                    if (ch == 2)
                        account_type = "savings";
                case 2:
                    try {
                        AAS.searchAccount(account_type);
                    } catch (SmileException e) {

                        e.printStackTrace();
                    }
                    ch = 0;
                    break;
                case 3:
                    smile.message("Exiting Work");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }


        } while (ch != 3);
    }

    public void ViewAccountMethod() {
        String account_type = null;
        int ch = 0;
        int ID = 0;
        do {
            try {
                smile.message("");
                smile.message("Will you be Viewing a Checking or Savings Account?");
                smile.message("1) Checking");
                smile.message("2) Savings");
                smile.message("3) Stop Search");
                ch = Integer.parseInt(scanner.nextLine());
                smile.message("");
            } catch (NumberFormatException e) {
            }

            switch (ch) {
                case 1:
                    account_type = "checking";
                    try {
                        smile.message("Search by entering Customer ID");
                        smile.message("");
                        try {
                            ID = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }

                        VAS.viewAccount(ID, account_type);
                        ID = 0;
                    } catch (SmileException e) {
                        smile.warn("No account found for Customer ID " + ID);
                        // e.printStackTrace();
                    }
                    ch = 0;
                    break;
                case 2:
                    account_type = "savings";
                    try {
                        smile.message("Search by entering Customer ID");
                        try {
                            ID = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }

                        VAS.viewAccount(ID, account_type);
                        ID = 0;
                    } catch (SmileException e) {
                        smile.warn("No account found for Customer ID " + ID);
                        // e.printStackTrace();
                    }
                    ch = 0;
                    break;
                case 3:
                    smile.message("Stopping Search...");
                    smile.message("");
                    break;

                default:
                    smile.error();
            }

        } while (ch != 3);
    }

    public void ViewTransactionLog() {
        int filter = 0;
        String filter_spec = null;
        do {


            try {
                smile.message("");
                smile.message("Filter Transaction Logs By Your Choice");
                smile.message("1) View All");
                smile.message("2) View By Date");
                smile.message("3) View By Type");
                smile.message("4) View By Customer ID");
                smile.message("5) View By Account Number");
                smile.message("6) View By Customer Name");
                smile.message("7) Exit");
                filter = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {

            }

            switch (filter) {
                case 1:
                    try {
                        List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                        if (transactions != null) {
                            smile.message("List of Transactions");
                            int val = transactions.size();
                            for (int i = 0; i < val; i++) {

                                smile.message((i+1) +") " + transactions.get(i));
                            }
                        } else {
                            smile.message("Sorry no transactions");
                        }
                    } catch (SmileException e) {

                    }
                    break;
                case 2:
                    try {
                        smile.message("Enter a Date to search by.");
                        smile.message("This Searches the date you give to today");
                        smile.message("Must be this format: (YYYY-MM-DD)");
                        filter_spec = scanner.nextLine();
                        List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                        if (transactions != null) {
                            smile.message("List of Transactions");
                            int val = transactions.size();
                            for (int i = 0; i < val; i++) {

                                smile.message((i+1) +") " + transactions.get(i));
                            }
                        } else {
                            smile.message("Sorry no transactions");
                        }
                    } catch (SmileException e) {

                    }
                    break;
                case 3:
                    try {
                        int ch = 0;
                        smile.message("Withdrawl, Deposit or Transfer?");
                        smile.message("1) Withdrawl");
                        smile.message("2) Deposit");
                        smile.message("3) Transfer: Outbound");
                        smile.message("3) Transfer: Inbound");
                        try {
                            ch = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {

                        }
                        if (ch == 1)
                            filter_spec = "Withdrawl";
                        if (ch == 2)
                            filter_spec = "Deposit";
                        if (ch == 3)
                            filter_spec = "Transfer: Outbound";
                        if (ch == 4)
                            filter_spec = "Transfer: Inbound";
                        if (ch > 4 || ch < 1)
                            break;
                        List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                        if (transactions != null) {
                            smile.message("List of Transactions");
                            int val = transactions.size();
                            for (int i = 0; i < val; i++) {

                                smile.message((i+1) +") " + transactions.get(i));
                            }
                        } else {
                            smile.message("Sorry no transactions");
                        }
                    } catch (SmileException e) {

                    }
                    break;
                case 4:
                    try {
                        smile.message("Enter a Customer ID");
                        filter_spec = scanner.nextLine();
                        try {
                            List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                            if (transactions != null) {
                                smile.message("List of Transactions");
                                int val = transactions.size();
                                for (int i = 0; i < val; i++) {

                                    smile.message((i+1) +") " + transactions.get(i));
                                }
                            }else if(transactions.size()==0){
                                smile.message("");
                                smile.message("No results matching this filter");
                                smile.message("");
                            }
                            else {
                                smile.message("Sorry no transactions");
                            }
                        } catch (NumberFormatException e) {
                            smile.message(filter_spec + " is not a number.");
                        }
                    } catch (SmileException e) {
                        smile.message(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        smile.message("Enter an Account Number");
                        filter_spec = scanner.nextLine();
                        try {
                            List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                            if (transactions != null) {
                                smile.message("List of Transactions");
                                int val = transactions.size();
                                for (int i = 0; i < val; i++) {

                                    smile.message((i+1) +") " + transactions.get(i));
                                }
                            } else {
                                smile.message("Sorry no transactions");
                            }
                        } catch (NumberFormatException e) {
                            smile.message(filter_spec + " is not a number.");
                        }
                    } catch (SmileException e) {
                        smile.message(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        smile.message("Enter a Name");
                        filter_spec = scanner.nextLine();
                        List<Transactions> transactions = find.findTransactions(filter_spec, filter);
                        if (transactions != null) {
                            smile.message("List of Transactions");
                            int val = transactions.size();
                            for (int i = 0; i < val; i++) {

                                smile.message((i+1) +") " + transactions.get(i));
                            }
                        } else {
                            smile.message("Sorry no transactions");
                        }
                    } catch (SmileException e) {

                    }
                    break;
                case 7:
                    smile.message("Exiting..");
                    smile.message("");
                    break;
                default:
                    smile.error();
            }

        } while (filter != 7);


    }

}