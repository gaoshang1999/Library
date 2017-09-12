package business;

import java.io.Serializable;
import java.time.LocalDate;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import library.domain.CheckoutRecordEntry;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord;


	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}


	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}


	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;
		this.checkoutRecord = new CheckoutRecord();
	}


	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecordEntry checkoutBook(BookCopy bookCopy,LocalDate checkoutDate,LocalDate dueDate){
		DataAccess da = new DataAccessFacade();
		CheckoutRecordEntry checkoutEntry = new CheckoutRecordEntry(bookCopy,checkoutDate,dueDate);
		bookCopy.changeAvailability();
		checkoutRecord.addCheckoutEntry(checkoutEntry);
		return checkoutEntry;
	}


	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
