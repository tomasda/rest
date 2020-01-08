package com.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldRest {

	@GET
	public Response sayHello() {
		return Response.ok("Hello world from api rest",MediaType.APPLICATION_JSON).build();
		/**
		 * Para consumir el servicio acceder a la siguiente URL
		 * http://localhost:8080/REST_SERVER/helloworld
		 */
	}
}
