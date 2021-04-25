package com.smile.bank.functions;

import java.util.Scanner;

import com.smile.bank.consoles.OpenAccountConsole;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.impl.NewTransactionDAOImpl;
import com.smile.bank.functions.service.ApproveAccountService;
import com.smile.bank.functions.service.NewTransactionService;
import com.smile.bank.functions.service.OpenAccountService;
import com.smile.bank.functions.service.ViewAccountService;
import com.smile.bank.functions.service.impl.ApproveAccountServiceImpl;
import com.smile.bank.functions.service.impl.NewTransactionServiceImpl;
import com.smile.bank.functions.service.impl.OpenAccountServiceImpl;
import com.smile.bank.functions.service.impl.ViewAccountServiceImpl;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.OpenAccount;

public class Functions {
//bulk of the methods
	SmileLog smile = new SmileLog();
	Scanner scanner = new Scanner(System.in);
	OpenAccountService OAS = new OpenAccountServiceImpl();
	ApproveAccountService AAS = new ApproveAccountServiceImpl();
	ViewAccountService VAS = new ViewAccountServiceImpl();
	NewTransactionService NTC = new NewTransactionServiceImpl();

	// *************Customer Methods********************
	public void OpenAccountMethod(String email) {

		OpenAccountConsole open = new OpenAccountConsole();

		open.openAccConsole(email);

	}

	public void OpenCheckingMethod(String email) {
		OpenAccount newAccount = new OpenAccount();
		try {
			smile.message("Please Enter a Starting Balance for Checkings: ");
			newAccount.setBalance(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException e) {
			smile.message("You entered a bad number!");
			smile.eventFail(e);
		}
		try {
			newAccount.setCustomer_id(OAS.quickfindID(email));
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
		try {
			smile.message("Please Enter a Starting Balance for Savings: ");
			newAccount.setBalance(Double.parseDouble(scanner.nextLine()));
		} catch (NumberFormatException e) {
			smile.message("You entered a bad number!");
			smile.eventFail(e);
		}
		try {
			newAccount.setCustomer_id(OAS.quickfindID(email));
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

	// *************Employee Methods********************
	public void AccountApprovalMethod() {
		String account_type = null;
		int ch = 0;
		do {
			try {

				smile.message("Will you be Approving/Denying Checking or Savings?");
				smile.message("1) Checking");
				smile.message("2) Savings");
				smile.message("3) Exit Work Mode");
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}

			switch (ch) {
			case 1:
				account_type = "checking";
				ch = 0;
				break;
			case 2:
				account_type = "savings";
				ch = 0;
				break;
			case 3:
				smile.message("Exiting Work Mode...");
				smile.message("");
				break;

			default:
				smile.error();
			}

			try {
				AAS.searchAccount(account_type);
			} catch (SmileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	}

	public void WithdrawMethod() {
		int customer_id = 0;
		int acc_num = 0;
		String account_type = null;
		double balance = 0;
		double amount = 0;

		int ch = 0;
		smile.message("What is your customer ID? ");
		try {
			customer_id = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
		}

		smile.message("What is your account number? ");
		try {
			acc_num = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
		}

		smile.message("How much would you like to withdraw? ");
		try {
			amount = Double.parseDouble(scanner.nextLine());
		} catch (NumberFormatException e) {
		}
		do {
			try {
				smile.message("");
				smile.message("Will you be Withdrawing from Your Checking or Savings Account?");
				smile.message("1) Checking");
				smile.message("2) Savings");
				smile.message("3) Exit");
				ch = Integer.parseInt(scanner.nextLine());
				smile.message("");
			} catch (NumberFormatException e) {
			}

			switch (ch) {
			case 1:
				account_type = "checking";
				// VAS.viewAccount(ID, account_type);
				try {
					NTC.withdrawAcc(customer_id, acc_num, account_type, balance, amount);
				} catch (SmileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ch = 0;
				break;
			case 2:
				account_type = "savings";
				// smile.message("Search by entering Customer ID");
				try {
				} catch (NumberFormatException e) {

				}

				// VAS.viewAccount(ID, account_type);
				try {
					NTC.withdrawAcc(customer_id, acc_num, account_type, balance, amount);
				} catch (SmileException e) {
					// TODO Auto-generated catch block
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

	public void DepositMethod() {

	}

	public void TransferMethod() {

	}

	public void SendMoneyMethod() {

	}

	public void ViewAccountCustomer(int ID) {
		String account_type = null;
		int ch = 0;

		do {
			try {
				smile.message("");
				smile.message("Will you be Viewing Your Checking or Savings Account?");
				smile.message("1) Checking");
				smile.message("2) Savings");
				smile.message("3) Exit");
				ch = Integer.parseInt(scanner.nextLine());
				smile.message("");
			} catch (NumberFormatException e) {
			}

			switch (ch) {
			case 1:
				account_type = "checking";
				try {

					VAS.viewAccount(ID, account_type);

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
					} catch (NumberFormatException e) {

					}

					VAS.viewAccount(ID, account_type);
				} catch (SmileException e) {
					smile.warn("No account found for Customer ID " + ID);
					// e.printStackTrace();
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

}