package business;

import java.io.Serializable;

public class AlreadyExistsException extends Exception implements Serializable {

	public AlreadyExistsException() {
		super();
	}
	public AlreadyExistsException(String msg) {
		super(msg);
	}
	public AlreadyExistsException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}
