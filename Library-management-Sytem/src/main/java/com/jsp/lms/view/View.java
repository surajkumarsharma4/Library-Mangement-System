package com.jsp.lms.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.jsp.lms.dao.Dao;
import com.jsp.lms.dao.DaoImplimentation;
import com.jsp.lms.entity.Book;
import com.jsp.lms.entity.Library;
import com.jsp.lms.entity.Status;

public class View {
public static void main(String[] args) {
	Scanner scanner= new Scanner(System.in);
	Dao dao= new DaoImplimentation();
	boolean stop=true;
	while(stop) {
	System.out.println("Welcome to library management system");
	System.out.println("1. add Library");
	System.out.println("2. add book to library");
	System.out.println("3. find book by book id");
	System.out.println("4. find books by library id");
	System.out.println("5. find books by author name");
	System.out.println("6. update book or library");
	System.out.println("7. delete book or library");
	System.out.println("enter the choice");
	int choice =scanner.nextInt();
	
	switch (choice) {
	case 1:
		try {
			Library library=new Library();
			
			library.setLibId(dao.generateLibraryId());				
			System.out.println("enter library name");
			scanner.nextLine();
			library.setLibrary_name(scanner.nextLine());		
			System.out.println("enter library email");
			library.setEmail(scanner.next());		
			System.out.println("enter library phone");
			library.setPhone(scanner.nextLong());	
			System.out.println("enter librarian name");
			scanner.nextLine();
			library.setLibrarian_name(scanner.nextLine());		
			System.out.println("enter library location");
			library.setLibrary_location(scanner.next());
			if(dao.saveLibrary(library)) {
				System.out.println("library added");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid details");
		}
		break;
	case 2:
		try {
			System.out.println("how many book do you want to add");
			int count=scanner.nextInt();
			
				System.out.println("In which Library id you want to add book");
				int libId=scanner.nextInt();
				while(count>0) {
				if(dao.isLibaryByIdPresent(libId)) {
					Book book=new Book();
					
					book.setId(dao.generateBookId());
					System.out.println("enter book title");
					scanner.nextLine();
					book.setTitle(scanner.nextLine());
					System.out.println("enter book aurthor");
					book.setAurthor(scanner.nextLine());
					System.out.println("enter the price");
					book.setPrice(scanner.nextDouble());
					System.out.println("enter published date yyyy-mm-dd");
					book.setPublished_date(LocalDate.parse(scanner.next()));
					System.out.println("enter issue date yyyy-mm-dd");
					book.setIssue_date(LocalDate.parse(scanner.next()));
					System.out.println("select the status \n1.LOST \n2.ISSUED \n3.AVIALABLE");					
					int options=scanner.nextInt();
					switch (options) {
					case 1:
						book.setStatus(Status.LOST);
						break;
					case 2:
						book.setStatus(Status.ISSUED);
						break;
					case 3:
						book.setStatus(Status.AVIALABLE);
						break;
					default:
						break;
					}
					book.setLibId(libId);
					if(dao.addBook(book)) {
						System.out.println("Book Added");
					}
					count--;
				}else {
						System.out.println("This Library is not exists, Please select another library");
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("please enter valid details");
		}
			break;
		case 3:{
			System.out.println("Enter Book ID");
			int id=scanner.nextInt();
			LinkedList<Book> book=dao.getBookById(id);
			System.out.println(book);
			break;
		}
		case 4:{
			System.out.println("Enter Library ID");
			int libraryId=scanner.nextInt();
			LinkedList<Book> book=dao.getBookByLibraryId(libraryId);
			int count1=1;
		
			for(Book e:book) {
				System.out.println("For Book "+count1+"-->");
				System.out.println(e);
				count1++;
			}
			break;
		}
		case 5:{
			System.out.println("Enter Author Name");
			scanner.nextLine();
			String author=scanner.nextLine();
			LinkedList<Book> book=dao.getBookByAurthor(author);
			int count1=1;
		
			for(Book e:book) {
				System.out.println("For Book "+count1+"-->");
				System.out.println(e.toString());
				System.out.println("_________________________");
				count1++;
			}
			break;
			
		}
		case 6:
		{
			System.out.println("Where you want to update, Choose \n1. For Library \n2. For Book");
			int choice2=scanner.nextInt();
			switch(choice2) {
			case 1:{
				System.out.println("Which Library ID you want to update");
				int choice3=scanner.nextInt();
				if(dao.isLibaryByIdPresent(choice3)) {
					System.out.println("In Library What you want to change? \n1. Name of Library \n2. Location of Library \n3. Email Id of Library \n4. Phone number of library \n5. Librarian Name of library");
					int choice4=scanner.nextInt();
					if(dao.updateLibraryTable(choice3, choice4)>0) {
						System.out.println("Updated Successfully");
					}
				}else {
					System.out.println("No Library present for this library id");
				}
				break;
			}
			case 2:{
				System.out.println("Which book ID you want to update?");
				int choice3=scanner.nextInt();
				if(dao.isBookIdPresent(choice3)) {
					System.out.println("In Book What you want to change? \n1. Title \n2. Author \n3. Price \n4. Published Date \n5. Issued Date \n6. Status \n7. Library ID");
					int choice4=scanner.nextInt();
					if(dao.updateBookTable(choice3, choice4)>0) {
						System.out.println("Updated Successfully");
					}
				}else {
					System.out.println("No Book present for this book id");
				}
				break;
			}
			default:{
				break;
			}
			}
		}
		break;
		case 7:{
			System.out.println("Where you want to delete, Choose \n1. For Library \n2. For Book");
			int choice3=scanner.nextInt();
			switch(choice3) {
			case 1:{
				System.out.println("Which Library you want to delete");
				int choice4=scanner.nextInt();
				if(dao.isLibaryByIdPresent(choice4)) {
					dao.deleteLibraryById(choice4);
					System.out.println("library deleted sucessfully");
				}else {
					System.out.println("No library present for this libId");
				}
				break;
			}
			case 2:{
				System.out.println("Which book you want to delete");
				int choice5=scanner.nextInt();
				if(dao.isBookIdPresent(choice5)) {
					dao.deleteBookById(choice5);
					System.out.println("book deleted sucessfully");
				}else {
					System.out.println("No Book present for this book id");
				}
			}
			break;
			
		}
		}
		default:break;
	}
	System.out.println("Do you want to continue type 1 or type 0 for exit");
	if(scanner.nextInt()==0) {
		stop=false;
	}
}
	
}
}
