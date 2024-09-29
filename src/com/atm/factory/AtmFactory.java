package com.atm.factory;

import java.util.Scanner;

import com.atm.bank.BankAccount;
import com.atm.persistence.AtmInterface;

public class AtmFactory {
	
	private AtmInterface atmInterface;

	public AtmFactory(AtmInterface atmInterface) {
		
		this.atmInterface = atmInterface;
	}
	
//	monitoring logic
	
	public void AtmMachine() {
		
		Scanner scanner = new Scanner(System.in);
		Integer accountNumber = null;
		Integer pin = null;
		System.out.println("Account Number : ");
		accountNumber = scanner.nextInt();
		
		System.out.println("pin");
		pin = scanner.nextInt();
		
		BankAccount bankAccount = atmInterface.authenticateUser(accountNumber, pin);
		
		
		
		if (bankAccount!=null) {
			
			while (true) {
				System.out.println("1. withdraw \n 2. deposit \n 3. chech balance");
				
				Integer choice = scanner.nextInt();
				
				switch (choice) {
				case 1: {
						System.out.println("Enter amount to withdraw : ");
						Float amount = scanner.nextFloat();
						Float balance = atmInterface.withdrawCash(amount);
						if (balance != -1) {
							System.out.println("Remaining balance = " + balance +"\n Transaction success");

						}
						else {
							System.out.println("Insufficient Balance. \n Transaction failed");
						}
						break;
				}
				case 2 :{
						System.out.println("Enter amount to deposit : ");
						Float amount = scanner.nextFloat();
						Float balance = atmInterface.depositCash(amount);
						System.out.println("Remaining balance = " + balance+"\n Transaction success");
						break;
						
				}
				case 3:{
						System.out.println(" Account balance = "+ atmInterface.checkBalance() +"\n Transaction success");
						break;
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
			}
			
		}
		else {
			System.out.println("Bank error.............");
		}
	}
	
	
	

}
