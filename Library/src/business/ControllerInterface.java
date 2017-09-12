package business;

import java.time.LocalDate;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import library.domain.CheckoutRecordEntry;
import library.domain.CheckoutTableData;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();

	public int addBookCopy(String isbn) throws AuthException, NotExistsException;
	public CheckoutTableData checkoutBook(String memberId,String isbn) throws NotExistsException;
	public List<CheckoutTableData> readAllCheckouts();
	public List<CheckoutTableData> readCheckoutsByMemberId(String memberId) throws NotExistsException;


}
