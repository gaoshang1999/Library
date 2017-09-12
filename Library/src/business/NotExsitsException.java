package business;

import java.io.Serializable;

public class NotExsitsException extends Exception implements Serializable {

	public NotExsitsException() {
		super();
	}
	public NotExsitsException(String msg) {
		super(msg);
	}
	public NotExsitsException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}
