package com.tricast.managers.exceptions;

public class OddsException extends SportsbookException {
  
	private static final long serialVersionUID = 8694508102304233639L;

	public OddsException(String message, Throwable cause) {
        super(message, cause);
    }

    public OddsException(String message) {
        super(message);
    }
}
