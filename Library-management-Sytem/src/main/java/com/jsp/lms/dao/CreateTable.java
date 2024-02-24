package com.jsp.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
public static void main(String[] args) {
	try {
		Class.forName("org.postgresql.Driver");
		String url="jdbc:postgresql://localhost:5432/library_management_system";
		String user="postgres";
		String password="root";
		Connection connection=DriverManager.getConnection(url,user,password);
		System.out.println("connection");
		Statement s=connection.createStatement();
//		String libraryTable="create table library(libId numeric primary key,library_location varchar(30) ,library_name varchar(30) ,email varchar(40) ,phone bigint ,librarian_name varchar(30) )";
		String bookTable="create table book(id numeric primary key,title varchar(30),author varchar(30) ,price numeric(10,2),published_date date ,issue_date date ,status varchar(15) ,library_id numeric references library(libId))";
		s.execute(bookTable);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
