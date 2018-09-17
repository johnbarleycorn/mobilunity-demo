package com.staxter.userrepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserAlreadyExistsException extends Exception {

	public UserAlreadyExistsException() {
		super();
	}

	public UserAlreadyExistsException(String message) {
		super(message);
		Set<Integer> set1 = new HashSet(Arrays.asList(new int[] {1,2,3}));
		set1.removeAll(Arrays.asList(new int[] {1}));
	}

}i
