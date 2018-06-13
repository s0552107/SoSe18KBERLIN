package de.htwBerlin.ai.kbe.services;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.storage.UserBook;

@Path("/auth")
public class UserWebService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getToken(@QueryParam("userId") String userId) {
		try {
			Boolean bool = UserBook.getInstance().findUser(userId);
			if (bool) {
				String token = UserBook.getInstance().createToken(userId);
				return Response.status(200).entity(token).build();
			}
			else
				return Response.status(403).entity("no authification").build();
		}
		catch (Exception e)
		{
			return Response.status(403).entity(e.getMessage()).build();
		}
		//Response.ok(userId).build();
	}

}
