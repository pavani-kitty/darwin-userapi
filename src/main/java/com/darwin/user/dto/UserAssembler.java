package com.darwin.user.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.darwin.user.UserController;

/**
* UserAssembler is the helper class which is used to convert the entity objects 
* to representation models.
* 
* @author Pavani
*/

@Component
public class UserAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {

	@Override
	public EntityModel<UserDTO> toModel(UserDTO userDTO) {
		Link selfLink = linkTo(methodOn(UserController.class).getUser(userDTO.getId())).withSelfRel();

		return EntityModel.of(userDTO, Arrays.asList(selfLink));
	}

	public CollectionModel<EntityModel<UserDTO>> toCollectionModel(List<UserDTO> users) {
		List<EntityModel<UserDTO>> userDTOs = users.stream().map(this::toModel).collect(Collectors.toList());
		Link link = linkTo(methodOn(UserController.class).getUsers()).withSelfRel();

		return CollectionModel.of(userDTOs, Arrays.asList(link));
	}
}
