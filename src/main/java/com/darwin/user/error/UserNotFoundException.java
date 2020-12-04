package com.darwin.user.error;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Integer userID) {
		super("Could not find user with ID - " + userID);
	}
}
