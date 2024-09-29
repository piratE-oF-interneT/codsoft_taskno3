package com.atm.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class GetConnection {
	
	private DataSource dataSource;

	public GetConnection(DataSource dataSource) {
		
		this.dataSource = dataSource;
	}
	
	public  static Connection getConnection() {
		
		Connection connection = null;
		
		try {
			connection = dataSource.getConnection();
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
			
		}
		
		return connection;
	}
	
	

}
