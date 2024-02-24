package com.jsp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
public Connection getConnection() {

	try {
		
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/library_management_system","postgres","root");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
