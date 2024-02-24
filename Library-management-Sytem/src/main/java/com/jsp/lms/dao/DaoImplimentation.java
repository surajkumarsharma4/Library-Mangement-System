package com.jsp.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import com.jsp.lms.entity.Book;
import com.jsp.lms.entity.Library;
import com.jsp.lms.entity.Status;

public class DaoImplimentation implements Dao{

	Scanner sc=new Scanner(System.in);
	@Override
	public boolean saveLibrary(Library library) {
		Connection connection=new CreateConnection().getConnection();
		try {
		
			PreparedStatement preparedStatement= connection.prepareStatement("insert into library values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, library.getLibId());
			preparedStatement.setString(2, library.getLibrary_location());
			preparedStatement.setString(3, library.getLibrary_name());
			preparedStatement.setString(4, library.getEmail());
			preparedStatement.setLong(5, library.getPhone());
			preparedStatement.setString(6, library.getLibrarian_name());
			
			int result=preparedStatement.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		Connection connection=new CreateConnection().getConnection();
		try {
			PreparedStatement ps=connection.prepareStatement("insert into Book values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAurthor());
			ps.setDouble(4, book.getPrice());
			ps.setObject(5, Date.valueOf(book.getPublished_date()));
			ps.setObject(6, Date.valueOf(book.getIssue_date()));
			ps.setString(7, book.getStatus().toString());
			ps.setInt(8, book.getLibId());
			int result=ps.executeUpdate();
			if(result>0) {
				return true;
				}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean isLibaryByIdPresent(int libId) {
		// TODO Auto-generated method stub
		Connection connection= new CreateConnection().getConnection();
		try {
			Statement st=connection.createStatement();
			st.execute("select libId from library");
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				if(libId==rs.getInt(1)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid data");
		}
		
		return false;
	}
	@Override
	public int generateLibraryId() {
		// TODO Auto-generated method stub
		int id=1;
		
			
			try {
				Statement st = new CreateConnection().getConnection().createStatement();
				ResultSet rs=st.executeQuery("Select MAX(libId) from library ");
				
				if(rs.next()) {
					id=rs.getInt(1)+1;
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("please enter valid data");
			}
			
		return id;
	}
	@Override
	public int generateBookId() {
		// TODO Auto-generated method stub
		int id=1;
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			st.execute("select MAX(id) from book;");
			ResultSet rs=st.getResultSet();
			if(rs.next()) {
				id=rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid data");
		}
		
		return id;
	}
	@Override
	public LinkedList<Book> getBookById(int id) {
		// TODO Auto-generated method stub
		LinkedList<Book> books= new LinkedList<Book>();
		try {
			Connection con=new CreateConnection().getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from book where id="+id);
			if(rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAurthor(rs.getString(3));
				book.setPrice(rs.getDouble(4));
				book.setPublished_date(rs.getDate(5).toLocalDate());
				book.setIssue_date(rs.getDate(6).toLocalDate());
				String status=rs.getString(7);
				if(status.equals(Status.AVIALABLE.toString())) {
					book.setStatus(Status.AVIALABLE);
				}else if(status.equals(Status.ISSUED.toString())) {
					book.setStatus(Status.ISSUED);
				}else {
					book.setStatus(Status.LOST);
				}
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid data");
		}
		return books;
		
	}
	@Override
	public LinkedList<Book> getBookByLibraryId(int libId) {
		// TODO Auto-generated method stub
		LinkedList<Book> books=new LinkedList<>();
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			ResultSet rs=st.executeQuery("select * from book,library where libId="+libId);
			while(rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAurthor(rs.getString(3));
				book.setPrice(rs.getDouble(4));
				book.setPublished_date(rs.getDate(5).toLocalDate());
				book.setIssue_date(rs.getDate(6).toLocalDate());
				String status=rs.getString(7);
				if(status.equals(Status.AVIALABLE.toString())) {
					book.setStatus(Status.AVIALABLE);
				}else if(status.equals(Status.ISSUED.toString())) {
					book.setStatus(Status.ISSUED);
				}else {
					book.setStatus(Status.LOST);
				}
				book.setLibId(libId);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid data");
		}
		return books;
		
	}
	@Override
	public LinkedList<Book> getBookByAurthor(String AurthorName) {
		LinkedList<Book> books=new LinkedList<>();
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			ResultSet rs=st.executeQuery("select * from book where author= '"+AurthorName+"'");
			while(rs.next()) {
				Book book=new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAurthor(AurthorName);
				book.setPrice(rs.getDouble(4));
				book.setPublished_date(rs.getDate(5).toLocalDate());
				book.setIssue_date(rs.getDate(6).toLocalDate());
				String status=rs.getString(7);
				if(status.equals(Status.AVIALABLE.toString())) {
					book.setStatus(Status.AVIALABLE);
				}else if(status.equals(Status.ISSUED.toString())) {
					book.setStatus(Status.ISSUED);
				}else {
					book.setStatus(Status.LOST);
				}
				book.setId(rs.getInt(8));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid data");
		}
		return books;
	}
	@Override
	public int updateLibraryTable(int libID, int choice) {
		int count=0;
		try {
			boolean flagChoice=true;
			Statement st=new CreateConnection().getConnection().createStatement();
			while(flagChoice) {
				switch(choice) {
				case 1:{
					System.out.println("new library name--");
					count=st.executeUpdate("update library set name='"+sc.nextLine()+"' where libId="+libID);
					flagChoice=false;
					break;
				}
				case 2:{
					System.out.println("new Location Name for library--");
					count=st.executeUpdate("update library set library_location='"+sc.nextLine()+"' where libId="+libID );
					flagChoice=false;
					break;
				}
				case 3:{
					System.out.println("What is your new Email ID for library");
					count=st.executeUpdate("update library set email='"+sc.nextLine()+"' where libId="+libID);
					flagChoice=false;
					break;
				}
				case 4:{
					System.out.println("What is your new phone number for library");
					count=st.executeUpdate("update library set phone="+sc.nextLong()+" where libId="+libID);
					flagChoice=false;
					break;
				}
				case 5:{
					System.out.println("What is your new Librarian Name");
					count=st.executeUpdate("update library set librarian_name='"+sc.nextLine()+"' where libId="+libID);
					flagChoice=false;
					break;
				}
				default:{
					System.out.println("You have entered wrong choice, please choose correct option");
				}
			}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	@Override
	public int updateBookTable(int bookID, int choice) {
		int count=0;
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			boolean flagChoice=true;
			while(flagChoice) {
				switch(choice) {
				case 1:{
					System.out.println("New Title ");
					
					count=st.executeUpdate("update book set title='"+sc.nextLine()+"' where id="+bookID);
					flagChoice=false;
					break;
				}
				case 2:{
					System.out.println("new Author Name");
					
					count=st.executeUpdate("update book set aurthor='"+sc.nextLine()+"' where id="+bookID);
					flagChoice=false;
					break;
				}
				case 3:{
					System.out.println("new price--");
					count=st.executeUpdate("update book set price="+sc.nextDouble()+" where id="+bookID);
					flagChoice=false;
					break;
				}
				case 4:{
					System.out.println("new published Date in yyyy-mm-dd");
					
					count=st.executeUpdate("update book set published_date='"+Date.valueOf(LocalDate.parse(sc.nextLine()))+"' where id="+bookID);
					flagChoice=false;
					break;
				}
				case 5:{
					System.out.println(" new issued Date in yyyy-mm-dd");
				
					count=st.executeUpdate("update book set issue_date='"+Date.valueOf(LocalDate.parse(sc.nextLine()))+"' where id="+bookID);
					flagChoice=false;
					break;
				}
				case 6:{
					
					boolean flagStatus=true;
					while(flagStatus) {
						System.out.println("Choose the status you want to change \n1. Available \n2. Issued \n3. Lost");
						switch(sc.nextInt()) {
						case 1:{
							
							count=st.executeUpdate("update book set status='"+Status.AVIALABLE.toString()+"' where id="+bookID);
							flagStatus=false;
							flagChoice=false;
							break;
						}case 2:{
							
							count=st.executeUpdate("update book set status='"+Status.ISSUED.toString()+"' where id="+bookID);
							flagStatus=false;
							flagChoice=false;
							break;
						}
						case 3:{
							
							count=st.executeUpdate("update book set status='"+Status.LOST.toString()+"' where id="+bookID);
							flagStatus=false;
							flagChoice=false;
							break;
						}
						default:{
							System.out.println("Wrong Choice ");
							
							
						}
					}
					}
					
				}
				case 7:{
					System.out.println("Which lib_id now you want to update");
					count=st.executeUpdate("update book set libId="+sc.nextInt()+" where id="+bookID);
					flagChoice=false;
					break;
				}
				
				default:{
					System.out.println("Wrong Choice ");
				}
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	@Override
	public boolean isBookIdPresent(int bookId) {
		Connection con=new CreateConnection().getConnection();
		try {
			Statement st=con.createStatement();
			st.execute("select id from book;");
			ResultSet rs=st.getResultSet();
			while(rs.next()) {
				if(bookId==rs.getInt(1)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public int deleteBookById(int id) {
		// TODO Auto-generated method stub
		int rs=0;
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			 rs=st.executeUpdate("delete from book where id="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	@Override
	public int deleteLibraryById(int id) {
		int rs=0;
		try {
			Statement st=new CreateConnection().getConnection().createStatement();
			rs=st.executeUpdate("delete from library where libId="+id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	
	

}
