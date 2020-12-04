package com.darwin.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import com.darwin.user.data.User;
import com.darwin.user.dto.UserAssembler;
import com.darwin.user.dto.UserDTO;
import com.darwin.user.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	
	@Mock
	UserAssembler userAssembler;

	ModelMapper modelMapper;

	@BeforeEach
	void setUp() {
		modelMapper = new ModelMapper();
	}

	@Test
	void testGetusers() {
		User user1 = new User("John Doe", "john@fx.com", "password", "07867530387", "sales", "salesman");
		User user2 = new User("Dheeraj Shah", "dheeraj@gmail.com", "password", "046-582-68787", "hr", "hrrep");
		User user3 = new User("Alex Smith", "alex@yahoo.com", "password", "0757860387", "electrical", "electrician");
		User user4 = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");
		User user5 = new User("Smitha Patil", "smitha@dheere.com", "password", "09876230387", "software", "programmer");
		User user6 = new User("Andy Haigh", "andy@google.com", "password", "09237530387", "logistics", "analyst");

		User[] userArray = { user1, user2, user3, user4, user5, user6 };
		List<User> expectedUsers = Arrays.asList(userArray);
		List<UserDTO> expectedUserDTOs = expectedUsers.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());

		when(userService.getUsers()).thenReturn(expectedUserDTOs);

		List<EntityModel<UserDTO>> entities = expectedUserDTOs.stream().map(user -> EntityModel.of(user))
				.collect(Collectors.toList());

		when(userAssembler.toCollectionModel(any())).thenReturn(CollectionModel.of(entities));

		ResponseEntity<CollectionModel<EntityModel<UserDTO>>> actualUserEntities = userController.getUsers();

		assertTrue(actualUserEntities.getStatusCode().is2xxSuccessful());
		assertTrue(actualUserEntities.getBody().getContent().size() == 6);

		assertEquals(user1.getName(),
				actualUserEntities.getBody().getContent().stream().findFirst().get().getContent().getName());
	}

	@Test
	void testGetUser() {
		User user = new User("Rachel H ", "rachel@tui.com", "password", "04567530387", "it", "QualityAnalyst");
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		EntityModel<UserDTO> userEntityModel = EntityModel.of(userDTO);

		when(userService.getUser(anyInt())).thenReturn(userDTO);

		when(userAssembler.toModel(any())).thenReturn(userEntityModel);

		ResponseEntity<EntityModel<UserDTO>> actualUserEntity = userController.getUser(2);

		assertNotNull(actualUserEntity);

		assertEquals(user.getEmail(), actualUserEntity.getBody().getContent().getEmail());
	}

	@Test
	void testCreateUser() {
		User user = new User("Keira Howley", "keira@bhs.com", "password", "045-675-30387", "clothing", "designer");
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		EntityModel<UserDTO> userEntityModel = EntityModel.of(userDTO);

		when(userAssembler.toModel(any())).thenReturn(userEntityModel);

		ResponseEntity<EntityModel<UserDTO>> actualUserEntity = userController.createUser(user);

		assertNotNull(actualUserEntity);
		assertEquals(user.getEmail(), actualUserEntity.getBody().getContent().getEmail());
	}

	@Test
	void testUpdateUser() {
		User user = new User(6,"Keira Howley", "keira@bhs.com", "password", "045-675-30387", "updated clothing",
				"updated designer");
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		EntityModel<UserDTO> userEntityModel = EntityModel.of(userDTO);

		when(userAssembler.toModel(any())).thenReturn(userEntityModel);

		ResponseEntity<EntityModel<UserDTO>> actualUserEntity = userController.updateUser(user);

		assertNotNull(actualUserEntity);
		assertEquals(user.getEmail(), actualUserEntity.getBody().getContent().getEmail());
	}

	@Test
	void testRemoveUser() {
		User user = new User("Keira Howley", "keira@bhs.com", "password", "045-675-30387", "updated clothing",
				"updated designer");
		
		ResponseEntity<Map<String,String>> responseEntity = userController.deleteUser(7);
		
		assertNotNull(responseEntity);
		
		assertEquals("Successfully removed the user with ID - 7", responseEntity.getBody().get("response"));

	}
}
