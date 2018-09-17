package com.staxter.demo.resources;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.staxter.demo.dto.UserDto;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserAlreadyExistsException;
import com.staxter.userrepository.UserRepository;

@RestController
public class UserController {

	private UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
	}

	@PostMapping(path = "/userservice/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserDto createUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
		return new UserDto(repository.createUser(user));
	}
}
