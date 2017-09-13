package dataaccess;

import java.util.HashMap;
import java.util.List;

import business.Book;
import business.LibraryMember;
import business.NotExistsException;
import dataaccess.DataAccessFacade.StorageType;
import library.domain.CheckoutRecordEntry;

public interface DataAccess {
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveBook(Book book);
	public LibraryMember searchMember(String memberId) throws NotExistsException;
	public Book searchBook(String isbn) throws NotExistsException;
}
