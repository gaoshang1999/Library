package business;

import java.io.Serializable;

public class NotExistsException extends Exception implements Serializable {

	public NotExistsException() {
		super();
	}
	public NotExistsException(String msg) {
		super(msg);
	}
	public NotExistsException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}
