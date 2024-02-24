package com.jsp.lms.dao;

import java.util.LinkedList;

import com.jsp.lms.entity.Book;
import com.jsp.lms.entity.Library;

public interface Dao {
public boolean saveLibrary(Library library);
public boolean addBook(Book book);
public boolean isLibaryByIdPresent(int libId);
public int generateLibraryId();
public boolean isBookIdPresent(int bookId);
public int generateBookId();
public LinkedList<Book> getBookById(int id);
public LinkedList<Book> getBookByLibraryId(int id);
public LinkedList<Book> getBookByAurthor(String AurthorName);
public int updateLibraryTable(int libID,int choice);
public int updateBookTable(int bookID,int choice);
public int deleteBookById(int id);
public int deleteLibraryById(int id);
}
