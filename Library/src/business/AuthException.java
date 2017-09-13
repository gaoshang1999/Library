package business;

import java.io.Serializable;

public class AuthException extends Exception implements Serializable {

	public AuthException() {
		super();
	}
	public AuthException(String msg) {
		super(msg);
	}
	public AuthException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}

