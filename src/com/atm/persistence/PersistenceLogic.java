package com.atm.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.atm.bank.BankAccount;

public class PersistenceLogic implements AtmInterface{
	
	
	private DataSource dataSource;
		
	public PersistenceLogic(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}

//	queries
	
//	1. authenticate
	
	private static final String AUTHENTICATION_QUERY = "SELECT accountnum,name,balance,pin from bankholders where accountnum = ? AND pin = ?"; 
	private static final String WITHDRAW_QUERY = "UPDATE bankholders SET balance = ? WHERE accountnum = ?"; 
	private static final String DEPOSIT_QUERY = "UPDATE bankholders SET balance = ? WHERE accountnum = ?"; 
	private static final String CHECK_BALANCE_QUERY = "SELECT balance from bankholders where accountnum = ?"; 

	BankAccount bankAccount = null;
	
	private Connection connection = null;
	
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	
	

	public BankAccount authenticateUser(Integer accountNumber, Integer pin) {
		// TODO Auto-generated method stub
		
		
		
		
		
		try {
			connection = dataSource.getConnection();
			psmt = connection.prepareStatement(AUTHENTICATION_QUERY);
			psmt.setInt(1, accountNumber);
			psmt.setInt(2, pin);
			
			rs = psmt.executeQuery();
			
			if (rs!=null) {
				while (rs.next()) {
					bankAccount = new BankAccount();
					bankAccount.setAccountNumber(rs.getInt(1));
					bankAccount.setName(rs.getString(2));
					bankAccount.setBalance(rs.getFloat(3));
					bankAccount.setPin(rs.getInt(4));
					
				}
			} else {
				
				bankAccount = null;

			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Invalid account details...........................................");
			
		}
		
		
		
		return bankAccount;
	}

	@SuppressWarnings("finally")
	@Override
	public Float withdrawCash(Float amount) {
		// TODO Auto-generated method stub
		
		Float updatedAmount = null;
		if (bankAccount.getBalance()>=amount) {
//			withdraw money
			updatedAmount = bankAccount.getBalance()-amount;
		
			
			
			try {
				connection = dataSource.getConnection();

				psmt = connection.prepareStatement(WITHDRAW_QUERY);
				psmt.setFloat(1, updatedAmount);
				psmt.setInt(2, bankAccount.getAccountNumber());
				
				rs = psmt.executeQuery();
			} 
			finally {
				bankAccount.setBalance(updatedAmount);

				return updatedAmount;
			}
		} else {
			
			return -1f;

		}
		
		
	}

	@SuppressWarnings("finally")
	@Override
	public Float depositCash(Float amount) {
		// TODO Auto-generated method stub
		
			Float updatedAmount = null;
		
//			deposit money
			
			
			try {
				connection = dataSource.getConnection();

				updatedAmount = bankAccount.getBalance()+amount;

				psmt = connection.prepareStatement(DEPOSIT_QUERY);
				psmt.setFloat(1, updatedAmount);
				psmt.setInt(2, bankAccount.getAccountNumber());
				
				rs = psmt.executeQuery();
			}
			finally {
				bankAccount.setBalance(updatedAmount);

				return updatedAmount;

		}
		
	}

	public Float checkBalance() {
		// TODO Auto-generated method stub
		return bankAccount.getBalance();
	}
	
	
	
	
	
	
	
	

}
