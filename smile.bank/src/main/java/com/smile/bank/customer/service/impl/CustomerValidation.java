package com.smile.bank.customer.service.impl;

public class CustomerValidation {

	public static boolean isValidName(String name) {
		if (name != null && name.matches("[a-zA-Z]{2,15} [a-zA-Z]{2,14}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidSSN(String ssn) {
		if (ssn != null && ssn.matches("[0-9]{9}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidEmail(String email) {
		if (email != null && email.matches("[^ ]{1,15}@[^ ]{3,14}")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidPassword(String password) {
		if (password != null && password.matches("[^ ]{8,16}")) {
			return true;
		} else {
			return false;
		}
	}
}
