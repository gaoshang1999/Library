package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Author;
import business.Book;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.NotExistsException;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess {
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String, Author> readAuthorMap();
	public void saveNewMember(LibraryMember member);
	public void saveBook(Book book);
	public LibraryMember searchMember(String memberId) throws NotExistsException;
	public Book searchBook(String isbn) throws NotExistsException;
	public Author searchAuthor(Author auth) throws NotExistsException;
	public void saveNewAuthor(Author auth);
}
