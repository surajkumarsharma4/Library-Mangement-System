package com.jsp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryTable {
public static void main(String[] args) {
	try {
		Class.forName("org.postgresql.Driver");
		String url="jdbc:postgresql://localhost:5432/library_management_system";
		String user="postres";
		String password="root";
		Connection connection=DriverManager.getConnection(url,user,password);
		Statement s=connection.createStatement();
		String query="create table library(library_id numeric primary key,library_location varchar(30) not null,library_name varchar(30) not null,email varchar(30) not null,phone bigint not null,)";
		s.execute(query);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
