package com.api.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.api.rest.beans.User;
import com.api.rest.core.ApplicationData;


@Path("/users")
@Consumes(value=MediaType.APPLICATION_JSON)
@Produces(value=MediaType.APPLICATION_JSON)
public class UserService {
	
	@Inject
	private ApplicationData app;
//	//User database pre-initialization
//	private final List<User> users = new ArrayList<>();
//	
//	public UserService() {
//		users.add(new User(1L,"Juan","1234"));
//		users.add(new User(2L,"Pepe","1234"));
//		users.add(new User(3L,"Ana","1234"));
//	}
	
	@GET
	public Response findAllUsers() {
		
		return Response.ok(this.app.getUsers()).build();
	}
	
	@POST
	public Response createUser(User userRequest) {
		userRequest.setId(this.app.getUsers().size()+1L);
		this.app.getUsers().add(userRequest);
		return Response.ok(userRequest).build();
	}
	
	@PUT
	public Response updateUser(User userRequest) {
		List<User> found = this.app.getUsers().stream().filter(x->userRequest.getId()==x.getId()).collect(Collectors.toList());
		if(found.isEmpty())
			return Response.status(Status.BAD_REQUEST).entity("User not Found").build();
		
		User updateUser = found.get(0);
		updateUser.setPassword(userRequest.getPassword());
		updateUser.setUsername(userRequest.getUsername());
		return Response.ok(updateUser).build();
	}
	
	@DELETE
	@Path("{userId}")
	public Response deleteUser(@PathParam("userId") long userId) {
		System.out.println("userId == "+userId);
		List<User> found = this.app.getUsers().stream().filter(x->userId==x.getId()).collect(Collectors.toList());
		if(found.isEmpty())
			return Response.status(Status.BAD_REQUEST).entity("User not Found").build();
		
		User updateUser = found.get(0);
		this.app.getUsers().remove(updateUser);
		return Response.noContent().build();
	}
	
	@HEAD
	public Response pingUserService() {
		return Response.noContent().header("running", true).build();
	}

}
