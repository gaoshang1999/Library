package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import library.domain.CheckoutRecordEntry;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 1268801666336692083L;

	List<CheckoutRecordEntry> checkoutEntries;

	public CheckoutRecord(){
		checkoutEntries = new ArrayList<>();
	}

	public void addCheckoutEntry(CheckoutRecordEntry entry){
		checkoutEntries.add(entry);
	}

	public List<CheckoutRecordEntry> getCheckoutEntries(){
		return this.checkoutEntries;
	}
}
