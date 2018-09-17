package com.staxter.userrepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

@Component
public class InMemoryUserRepository implements UserRepository {

	private AtomicInteger counter = new AtomicInteger(0);
	private Map<String, User> users = new ConcurrentHashMap<>();

	@Override
	public User createUser(User user) throws UserAlreadyExistsException {
		if (users.get(user.getUserName()) != null) {
			throw new UserAlreadyExistsException("User already exists, username: " + user.getUserName());
		}
		user.setId(String.valueOf(counter.getAndIncrement()));
		users.put(user.getUserName(), user);
		return user;
	}

}
