package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import library.domain.CheckoutRecordEntry;
import library.domain.CheckoutTableData;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override

	public List<Book> allBooks(){
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}

	@Override
	public List<LibraryMember> allLibraryMembers(){
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
		return retval;
	}

	@Override
	public void addNewMember(LibraryMember per) throws AlreadyExistsException{
		DataAccess da = new DataAccessFacade();
		if(da.readMemberMap().containsKey(per.getMemberId())) {
			throw new AlreadyExistsException("Error! Library Member already exists!");
		}
		da.saveNewMember(per);
	}

	public void addBook(Book b) throws AlreadyExistsException{
		DataAccess da = new DataAccessFacade();
		if (da.readBooksMap().containsKey(b.getIsbn())){
			throw new AlreadyExistsException("Error! Book already exists!");
		}
		da.saveBook(b);
	}
	@Override
	public int addBookCopy(String isbn) throws NotExistsException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> map = da.readBooksMap();
		if (!map.containsKey(isbn)) {
			throw new NotExistsException("Book is not exist");
		}

		Book book = map.get(isbn);
		book.addCopy();

		da.saveBook(book);

		return book.getNumCopies();
	}

	@Override
	public CheckoutTableData checkoutBook(String memberId, String isbn) throws NotExistsException {
		// TODO searchMember -> searchBook -> add entry
		DataAccess da = new DataAccessFacade();
		CheckoutTableData retVal = new CheckoutTableData();
		//try {
			LibraryMember member = da.searchMember(memberId);
			Book book = da.searchBook(isbn);
			BookCopy bookCopy = book.getFirstAvailableCopy();
			LocalDate checkoutDate = LocalDate.now();
			LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
			CheckoutRecordEntry lastEntry = member.checkoutBook(bookCopy, checkoutDate, dueDate);

			retVal.setCheckoutDate(lastEntry.getCheckoutDate());
			retVal.setCopyNumber(lastEntry.getBookCopy().getCopyNum());
			retVal.setDueDate(lastEntry.getDueDate());
			retVal.setIsbn(lastEntry.getBookCopy().getBook().getIsbn());
			retVal.setMemberId(member.getMemberId());
			da.saveNewMember(member);
			da.saveBook(book);
			return retVal;
		//}
//		catch (NoSuchElementException e) {
//			throw new NotExistsException();
//		}
//		catch (NullPointerException e) {
//			throw new NotExistsException();
//		}
	}

	// Not sure if we are going to use this.
	@Override
	public List<CheckoutTableData> readAllCheckouts() {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		List<CheckoutTableData> retVal = new ArrayList<>();
		Map<String,LibraryMember> membersHashMap = da.readMemberMap();
		membersHashMap.forEach((k,v) -> {
			for(CheckoutRecordEntry entry : v.getCheckoutRecord().getCheckoutEntries()){
				CheckoutTableData data = new CheckoutTableData();
				data.setCheckoutDate(entry.getCheckoutDate());
				data.setCopyNumber(entry.getBookCopy().getCopyNum());
				data.setDueDate(entry.getDueDate());
				data.setMemberId(v.getMemberId());
				data.setIsbn(entry.getBookCopy().getBook().getIsbn());
				retVal.add(data);
			}
		});

		return retVal;
	}

	@Override
	public List<CheckoutTableData> readCheckoutsByMemberId(String memberId) throws NotExistsException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		List<CheckoutTableData> retVal = new ArrayList<>();
		Map<String,LibraryMember> membersHashMap = da.readMemberMap();
		LibraryMember member = membersHashMap.get(memberId);
		if(member != null)
		{
			for(CheckoutRecordEntry entry : member.getCheckoutRecord().getCheckoutEntries()){
				CheckoutTableData data = new CheckoutTableData();
				data.setCheckoutDate(entry.getCheckoutDate());
				data.setCopyNumber(entry.getBookCopy().getCopyNum());
				data.setDueDate(entry.getDueDate());
				data.setMemberId(member.getMemberId());
				data.setIsbn(entry.getBookCopy().getBook().getIsbn());
				retVal.add(data);
			}
		}
		else
			throw new NotExistsException();
		return retVal;
	}


	@Override
	public List<CheckoutTableData> readCheckoutsByIsbn(String isbn) throws NotExistsException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		List<CheckoutTableData> retVal = new ArrayList<>();

		List<CheckoutTableData> allCheckOut = readAllCheckouts();

		for(CheckoutTableData ck : allCheckOut){
			if( ck.getIsbn().equals(isbn)){
                if(LocalDate.now().isAfter(ck.getDueDate())){
                	ck.setOverdue(true);
                }
				retVal.add(ck);
			}
		}
		if(retVal.isEmpty()) {
			throw new NotExistsException();
		}
		return retVal;
	}

	@Override
	public List<CheckoutTableData> readAllCheckoutsWithOverdue() throws NotExistsException{
		DataAccess da = new DataAccessFacade();
		List<CheckoutTableData> retVal = new ArrayList<>();

		List<CheckoutTableData> allCheckOut = readAllCheckouts();

		for(CheckoutTableData ck : allCheckOut){
            if(LocalDate.now().isAfter(ck.getDueDate())){
            	ck.setOverdue(true);
            }
			retVal.add(ck);
		}
		if(retVal.isEmpty()) {
			throw new NotExistsException();
		}
		return retVal;
	}

	@Override
	public LibraryMember searchMember(String memberId) throws NotExistsException {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		return da.searchMember(memberId);
		//return null;
	}

	@Override
	public Author searchAuthor(Author auth)  {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		try {
			return da.searchAuthor(auth);
		}catch(Exception ex){
			return null;
		}
		//return null;
	}

	@Override
	public void addNewAuthor(Author auth) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.saveNewAuthor(auth);
  }

	public void printCheckoutTable(TableView table) {
		ObservableList<CheckoutTableData> tableData = table.getItems();
		System.out.println(String.format("%15s \t %15s \t %15s \t %15s \t %15s","Member ID","ISBN","Copy Number", "Checkout Date", "Due Date"));
		for(CheckoutTableData data : tableData)
		{
			System.out.println(String.format("%15s \t %15s \t %15d \t %15s \t %15s",data.getMemberId(),data.getIsbn(),data.getCopyNumber(),data.getCheckoutDate().toString(),data.getDueDate().toString()));
		}
	}

}
