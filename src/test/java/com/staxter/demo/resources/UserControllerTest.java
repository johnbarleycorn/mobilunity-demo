package com.staxter.demo.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staxter.userrepository.User;
import com.staxter.userrepository.UserAlreadyExistsException;
import com.staxter.userrepository.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	UserRepository repository;

	User userIn;

	@Before
	public void setUp() {
		userIn = new User();
		userIn.setFirstName("John");
		userIn.setLastName("Doe");
		userIn.setUserName("jd");
		userIn.setPlainTextPassword("j0hnDo3");
	}

	@Test
	public void test_create_user_valid_case() throws Exception {
		User userOut = new User(userIn);
		userOut.setId("42");
		given(repository.createUser(any(User.class))).willReturn(userOut);

		mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(userIn))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(userOut.getId())))
				.andExpect(jsonPath("$.firstName", is(userOut.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(userOut.getLastName())))
				.andExpect(jsonPath("$.userName", is(userOut.getUserName())))
				.andExpect(jsonPath("$.plainTextPassword").doesNotHaveJsonPath());

		assertUserDeserializedAndRepositoryAccessed(userIn);
	}

	@Test
	public void test_create_user_already_exists() throws Exception {
		given(repository.createUser(any(User.class))).willThrow(new UserAlreadyExistsException());

		mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(userIn))).andDo(print()).andExpect(status().isConflict())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.code", is("USER_ALREADY_EXISTS")))
				.andExpect(jsonPath("$.description", is("A user with the given username already exists")));

		assertUserDeserializedAndRepositoryAccessed(userIn);
	}

	@Test
	public void test_create_user_invalid_input() throws Exception {
		userIn.setUserName(null);
		userIn.setPlainTextPassword(null);

		mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(userIn))).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.code", is("INVALID_INPUT")));

		verifyZeroInteractions(repository);
	}

	private void assertUserDeserializedAndRepositoryAccessed(User expectedUser) throws Exception {
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(repository, times(1)).createUser(captor.capture());

		User capturedUser = captor.getValue();
		assertNull(capturedUser.getId());
		assertNull(capturedUser.getHashedPassword());
		assertEquals(expectedUser.getFirstName(), capturedUser.getFirstName());
		assertEquals(expectedUser.getLastName(), capturedUser.getLastName());
		assertEquals(expectedUser.getUserName(), capturedUser.getUserName());
		assertEquals(expectedUser.getPlainTextPassword(), capturedUser.getPlainTextPassword());

	}
}