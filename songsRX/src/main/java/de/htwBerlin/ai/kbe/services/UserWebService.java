package de.htwBerlin.ai.kbe.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Random;

import javax.inject.Inject;
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
import de.htwBerlin.ai.kbe.storage.UsersDAO;
import de.htwBerlin.ai.kbe.bean.User;
import de.htwBerlin.ai.kbe.storage.SongsDAO;
import javax.inject.Inject;

@Path("/auth")
public class UserWebService {
	private UsersDAO usersDao;
	
	@Inject
	public UserWebService(UsersDAO dao) {
		this.usersDao = dao;
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getToken(@QueryParam("userId") String userId) {
		try {
//			Boolean bool = UserBook.getInstance().findUser(userId);
				
			User user = usersDao.findUserByUserId(userId);
			
			if (user != null) {
				String token = UserBook.getInstance().createToken(userId, user);
				return Response.status(200).entity(token).build();
			}
			else
				return Response.status(403).entity("no authification").build();
		}
		catch (Exception e)
		{
			return Response.status(403).entity("try fail lol").build();
		}
		//Response.ok(userId).build();
	}
	


}
