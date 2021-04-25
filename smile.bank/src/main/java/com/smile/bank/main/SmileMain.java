package com.smile.bank.main;


import com.smile.bank.consoles.SmileConsole;

//import java.util.Scanner;

//import org.apache.log4j.Logger;

import com.smile.bank.log.SmileLog;

public class SmileMain {
	// private static Logger log = Logger.getLogger(SmileMain.class);

	public static void main(String[] args) {
		//Scanner scanner = new Scanner(System.in);

		SmileLog smile = new SmileLog();
		SmileConsole menu = new SmileConsole();

		smile.hello();
		menu.consoleMain();
		smile.goodbye();
	}

}
