package com.darwin.user.dto;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@JsonRootName(value = "user")
@Relation(collectionRelation = "users")
@Data
public class UserDTO {
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String department;
	private String jobTitle;
}
