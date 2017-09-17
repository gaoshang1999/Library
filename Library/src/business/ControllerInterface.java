package business;

import java.util.List;

import business.Book;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.scene.control.TableView;
import library.domain.CheckoutRecordEntry;
import library.domain.CheckoutTableData;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();

	public void addNewMember(LibraryMember per)  throws AlreadyExistsException;
	public void addNewAuthor(Author auth);
	public int addBookCopy(String isbn) throws NotExistsException;

	public CheckoutTableData checkoutBook(String memberId,String isbn) throws NotExistsException;
	public List<CheckoutTableData> readAllCheckouts();
	public List<CheckoutTableData> readCheckoutsByMemberId(String memberId) throws NotExistsException;

	public List<CheckoutTableData> readCheckoutsByIsbn(String isbn) throws NotExistsException;
	public List<CheckoutTableData> readAllCheckoutsWithOverdue() throws NotExistsException;
	
	public List<Book> allBooks();
	public List<LibraryMember> allLibraryMembers();

	public LibraryMember searchMember(String memberId) throws NotExistsException;
	public Author searchAuthor(Author auth);
	public void addBook(Book newBook) throws AlreadyExistsException;
	public void printCheckoutTable(TableView table);

}
