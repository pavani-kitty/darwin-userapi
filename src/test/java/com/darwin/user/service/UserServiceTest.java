package com.darwin.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.darwin.user.data.User;
import com.darwin.user.data.UserRepository;
import com.darwin.user.dto.UserDTO;
import com.darwin.user.error.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	void testGetUsers() {
		User user1 = new User("John Doe", "john@fx.com", "password", "07867530387", "sales", "salesman");
		User user2 = new User("Dheeraj Shah", "dheeraj@gmail.com", "password", "046-582-68787", "hr", "hrrep");
		User user3 = new User("Alex Smith", "alex@yahoo.com", "password", "0757860387", "electrical", "electrician");
		User user4 = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");
		User user5 = new User("Smitha Patil", "smitha@dheere.com", "password", "09876230387", "software", "programmer");
		User user6 = new User("Andy Haigh", "andy@google.com", "password", "09237530387", "logistics", "analyst");

		User[] userArray = { user1, user2, user3, user4, user5, user6 };
		List<User> expectedUsers = Arrays.asList(userArray);

		when(userRepository.findAll()).thenReturn(expectedUsers);

		List<UserDTO> actualUsers = userService.getUsers();

		assertNotNull(actualUsers);
		assertTrue(6 == actualUsers.size());
	}

	@Test
	void testGetUser() {
		User expectedUser = new User("Alex Smith", "alex@yahoo.com", "password", "0757860387", "electrical",
				"electrician");
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(expectedUser));

		UserDTO actualUser = userService.getUser(4);

		assertNotNull(actualUser);
		assertEquals(expectedUser.getName(), actualUser.getName());
		assertEquals(expectedUser.getEmail(), actualUser.getEmail());
	}

	@Test
	void testUserNotFound() {
		when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> {
			userService.getUser(3);
		});
	}

	@Test
	void testCreateUser() {
		User expectedUser = new User("Smitha Patil", "smitha@dheere.com", "password", "09876230387", "software",
				"programmer");
		when(userRepository.save(any(User.class))).thenReturn(expectedUser);

		UserDTO actualUser = userService.createUser(expectedUser);

		assertNotNull(actualUser);
		assertEquals(expectedUser.getName(), actualUser.getName());
		assertEquals(expectedUser.getEmail(), actualUser.getEmail());
	}

	@Test
	void testUpdateUser() {
		User expectedUser = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");

		when(userRepository.save(any(User.class))).thenReturn(expectedUser);

		UserDTO actualUser = userService.updateUser(expectedUser);

		assertNotNull(actualUser);
		assertEquals(expectedUser.getName(), actualUser.getName());
		assertEquals(expectedUser.getEmail(), actualUser.getEmail());
	}

	@Test
	void testRemoveUser() {
		User expectedUser = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");

		when(userRepository.findById(anyInt())).thenReturn(Optional.of(expectedUser));

		userService.deleteUser(4);
	}
}
