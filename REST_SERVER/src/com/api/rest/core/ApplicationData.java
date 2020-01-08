package com.api.rest.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.api.rest.beans.User;

@ApplicationScoped
public class ApplicationData implements Serializable {
	private static final long serialVersionUID = 1L;
	//User database pre-initialization
	private final List<User> users = new ArrayList<>();
	
	public ApplicationData() {
		users.add(new User(1L,"Juan","1234"));
		users.add(new User(2L,"Pepe","1234"));
		users.add(new User(3L,"Ana","1234"));
	}

	public List<User> getUsers() {
		return users;
	}

	
}
