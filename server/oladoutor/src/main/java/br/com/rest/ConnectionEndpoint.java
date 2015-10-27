package br.com.rest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("/connection")
public class ConnectionEndpoint {
	
	@GET
	@Path("/test")
	public Response test() {		
		return Response.ok().build();
	}

}
