package com.darwin.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.darwin.user.data.User;

public class UserAssemblerTest {

	UserAssembler userAssembler;

	ModelMapper modelMapper;

	@BeforeEach
	void setUp() {
		userAssembler = new UserAssembler();
		modelMapper = new ModelMapper();
	}

	@Test
	void testToModel() {
		User user = new User("Alex Smith", "alex@yahoo.com", "password", "0757860387", "electrical", "electrician");
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		EntityModel<UserDTO> actualUserEntity = userAssembler.toModel(userDTO);

		assertNotNull(actualUserEntity);
		assertEquals(user.getEmail(), actualUserEntity.getContent().getEmail());
	}

	@Test
	void testToCollectionModel() {
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

		CollectionModel<EntityModel<UserDTO>> actualuserEntities = userAssembler.toCollectionModel(expectedUserDTOs);

		assertNotNull(actualuserEntities);
		assertEquals(user1.getEmail(),
				actualuserEntities.getContent().stream().findFirst().get().getContent().getEmail());
	}
}
