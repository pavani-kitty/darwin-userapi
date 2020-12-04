package com.darwin.user;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.darwin.user.data.User;
import com.darwin.user.dto.UserAssembler;
import com.darwin.user.dto.UserDTO;
import com.darwin.user.error.UserNotFoundException;
import com.darwin.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * UserController is the RestController class which serves all the RESTful
 * services endpoints.
 * 
 * @author Pavani
 */

@RestController
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserAssembler userAssembler;

	/**
	 * <p>
	 * This method retrieves all the users in the system.
	 * 
	 * @return all the users in the system
	 */
	@GetMapping("/users")
	public ResponseEntity<CollectionModel<EntityModel<UserDTO>>> getUsers() {
		List<UserDTO> users = userService.getUsers();
		log.info("Retrieved " + users.size() + " users");
		return ResponseEntity.ok(userAssembler.toCollectionModel(users));
	}

	/**
	 * <p>
	 * This method retrieves single user based on the user id.
	 * 
	 * @param userID
	 * @return specific user
	 */
	@GetMapping("/users/{userID}")
	public ResponseEntity<EntityModel<UserDTO>> getUser(@PathVariable("userID") Integer userID)
			throws UserNotFoundException {
		UserDTO userDTO = userService.getUser(userID);
		log.info("Retrieved user with id - " + userID);
		return ResponseEntity.ok(userAssembler.toModel(userDTO));
	}

	/**
	 * <p>
	 * This method creates/inserts a new user in the system.
	 * 
	 * @param user - user to be created
	 * @return Created user
	 */
	@PostMapping("/users")
	public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody User user) {
		UserDTO userDTO = userService.createUser(user);
		log.info("Created user with email - " + user.getEmail());
		return ResponseEntity.ok(userAssembler.toModel(userDTO));
	}

	/**
	 * <p>
	 * This method updates the user.
	 * 
	 * @param user - user to be updated
	 * @return Updated user information
	 */
	@PutMapping("/users")
	public ResponseEntity<EntityModel<UserDTO>> updateUser(@RequestBody User user) {
		UserDTO userDTO = userService.updateUser(user);
		log.info("Updated user with email - " + user.getEmail());
		return ResponseEntity.ok(userAssembler.toModel(userDTO));
	}

	/**
	 * <p>
	 * This method removes an user from the system.
	 * 
	 * @param userID - id of the user to be removed
	 * @return Successful removal message
	 */
	@DeleteMapping("/users/{userID}")
	public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("userID") Integer userID) throws UserNotFoundException {
		userService.deleteUser(userID);
		return ResponseEntity.ok(Collections.singletonMap("response", "Successfully removed the user with ID - " + userID));
	}
}
