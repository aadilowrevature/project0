package com.smile.bank.functions.service.impl;

public class Validator {
	public static boolean isValidTransaction(double balance, double request) {
		if (balance >= request) {
			return true;
		} else {
			return false;
		}

	}
	
	
}
