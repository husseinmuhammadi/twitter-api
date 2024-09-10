package com.javastudio.oauth.twitter.exceptions;

public class TwitterAuthenticationException extends Exception {

	public TwitterAuthenticationException(String message) {
		super(message);
	}

	public TwitterAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TwitterAuthenticationException(Throwable cause) {
		super(cause);
	}

	protected TwitterAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
