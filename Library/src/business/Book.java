package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 */
final public class Book implements Serializable {

	private static final long serialVersionUID = 6110690276685962829L;
	private SortedSet<BookCopy> copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;

	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		copies = new TreeSet<>();
		copies.add(new BookCopy(this, 1, true));

	}

	public void updateCopies(BookCopy updatedCopy) {
		// for(int i = 0; i < copies.length; ++i) {
		// BookCopy c = copies[i];
		// if(c.equals(copy)) {
		// copies[i] = copy;
		//
		// }
		// }
		if (copies.contains(updatedCopy)) {
			copies.add(updatedCopy);
		}
	}

	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for (BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;

	}

	public void addCopy() {
		copies.add(new BookCopy(this, copies.size() + 1, true));
	}

	@Override
	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		Book b = (Book) ob;
		return b.isbn.equals(isbn);
	}

	public BookCopy getFirstAvailableCopy() throws NoSuchElementException {
		if (copies == null) {
			throw new NoSuchElementException("No Copy available.");
		}
		else if(!isAvailable()){
			throw new NoSuchElementException("No Copy available.");
		}
		SortedSet<BookCopy> availableCopies = copies.stream().filter(c -> c.isAvailable())
				.collect(Collectors.toCollection(() -> new TreeSet<BookCopy>()));
		return availableCopies.first();
	}

	public boolean isAvailable() {
		SortedSet<BookCopy> availableCopies = copies.stream().filter(c -> c.isAvailable())
				.collect(Collectors.toCollection(() -> new TreeSet<BookCopy>()));
		if (availableCopies.size() > 0)
			return true;
		else
			return false;
	}
	
	public int getAvalibleNumerOfCopy() {
		SortedSet<BookCopy> availableCopies = copies.stream().filter(c -> c.isAvailable())
				.collect(Collectors.toCollection(() -> new TreeSet<BookCopy>()));
		return availableCopies.size() ;
	}

	@Override
	public String toString() {
		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
	}

	public int getNumCopies() {
		return copies.size();
	}

	public String getTitle() {
		return title;
	}

	public SortedSet<BookCopy> getCopies() {
		return copies;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public BookCopy getCopy(int copyNum) {
		for (BookCopy c : copies) {
			if (copyNum == c.getCopyNum()) {
				return c;
			}
		}
		return null;
	}

	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

}
