package com.darwin.user.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darwin.user.data.User;
import com.darwin.user.data.UserRepository;
import com.darwin.user.dto.UserDTO;
import com.darwin.user.error.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService is the Service class which implements the business logic for all
 * the RESTful service endpoints.
 * 
 * @author Pavani
 */
@Service
@Slf4j
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * <p>
	 * This method retrieves all the users in the system.
	 * 
	 * @return all the users in the system
	 */
	public List<UserDTO> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		log.debug("Retrieved " + users.size() + " users");
		return users.stream().map(user -> convertToDTO(user)).collect(Collectors.toList());
	}

	/**
	 * <p>
	 * This method retrieves single user based on the userID.
	 * 
	 * @param userID
	 * @return UserDTO - specific user
	 */
	public UserDTO getUser(Integer userID) {
		Optional<User> userOption = userRepository.findById(userID);
		log.debug("Retrieved user with id - " + userID);
		if (userOption.isPresent()) {
			return convertToDTO(userOption.get());
		}
		throw new UserNotFoundException(userID);
	}

	/**
	 * <p>
	 * This method creates a new user in the system.
	 * 
	 * @param user - user to be created
	 * @return UserDTO - Created user
	 */
	public UserDTO createUser(User user) {
		user.setPassword(getDecodedPassword(user.getPassword()));
		log.debug("Creating user with id - " + user.getId());
		return convertToDTO(userRepository.save(user));
	}

	/**
	 * <p>
	 * This method removes an user from the system.
	 * 
	 * @param userID - id of the user to be removed
	 */
	public void deleteUser(Integer userID) throws UserNotFoundException {
		userRepository.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
		log.debug("Removing user with id - " + userID);
		userRepository.deleteById(userID);
	}

	/**
	 * <p>
	 * This method updates the user information.
	 * 
	 * @param user to be updated
	 * @return Updated user
	 */
	public UserDTO updateUser(User user) {
		String userPassword = user.getPassword();
		if (userPassword != null && !userPassword.isEmpty()) {
			user.setPassword(getDecodedPassword(userPassword));
		}
		log.debug("Updating user with id - " + user.getId());
		return convertToDTO(userRepository.save(user));
	}

	private String getDecodedPassword(String encodedPassword) {
		return new String(Base64.getDecoder().decode(encodedPassword));
	}

	private UserDTO convertToDTO(User user) {
		ModelMapper modelMapper = new ModelMapper();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
}