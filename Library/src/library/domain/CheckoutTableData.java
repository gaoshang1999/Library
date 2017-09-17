package library.domain;

import java.time.LocalDate;

public class CheckoutTableData {
	private String memberId;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getCopyNumber() {
		return copyNumber;
	}
	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	private String isbn;
	private int copyNumber;
	private LocalDate checkoutDate;
	private LocalDate dueDate;

	private boolean isOverdue;
	public boolean getIsOverdue() {
		return isOverdue;
	}
	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}
}
