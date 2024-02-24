package com.jsp.lms.entity;

import java.util.List;

public class Library {
	private int libId;
	private String library_location;
	private String library_name;
	private String email;
	private long phone;
	private String librarian_name;
	private List<Book> books;
	
	
	public Library() {
		super();
	}

	public Library(int libId, String library_location, String library_name, String email, long phone,
			String librarian_name, List<Book> books) {
		super();
		this.libId = libId;
		this.library_location = library_location;
		this.library_name = library_name;
		this.email = email;
		this.phone = phone;
		this.librarian_name = librarian_name;
		this.books = books;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getLibId() {
		return libId;
	}
	public void setLibId(int libId) {
		this.libId = libId;
	}
	public String getLibrary_location() {
		return library_location;
	}
	public void setLibrary_location(String library_location) {
		this.library_location = library_location;
	}
	public String getLibrary_name() {
		return library_name;
	}
	public void setLibrary_name(String library_name) {
		this.library_name = library_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getLibrarian_name() {
		return librarian_name;
	}
	public void setLibrarian_name(String librarian_name) {
		this.librarian_name = librarian_name;
	}

	@Override
	public String toString() {
		return "Library [id=" + libId + ", library_location=" + library_location + ", library_name=" + library_name
				+ ", email=" + email + ", phone=" + phone + ", librarian_name=" + librarian_name + ", books=" + books
				+ "]";
	}
	
	
	
}
