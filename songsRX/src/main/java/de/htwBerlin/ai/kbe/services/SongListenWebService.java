package de.htwBerlin.ai.kbe.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.bean.User;
import de.htwBerlin.ai.kbe.storage.*;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.server.Permisson;
import org.glassfish.jersey.inject.hk2.RequestContext;
import de.htwBerlin.ai.kbe.storage.SongsDAO;
import javax.inject.Inject;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;


@Path("/userId/{userId}/songLists")


public class SongListenWebService {
	
	private SongListeDAO songListeDao;
	private UsersDAO usersDao;
	
	@Inject
	public SongListenWebService (SongListeDAO slDao, UsersDAO uDao) {
		this.songListeDao = slDao;
		this.usersDao = uDao;
			
	}
	
	@HeaderParam("Authorization")
	private String token;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Collection <SongListe> getToken(@PathParam("userId") String userId) {
	
		return songListeDao.findAllSongListen();
	}
	
	 @Context UriInfo uriInfo;
		@POST
	    @Permisson
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Produces(MediaType.TEXT_PLAIN)
		public Response createSongListe(@PathParam("userId") String userId, SongListe songListe) {

			String currentUser = UserBook.getInstance().findUserByToken(token);
			User user = usersDao.findUserByUserId(userId);

			if(currentUser == userId) {
				songListe.setId(null);
				songListe.setOwner(user);
				
				//songListeDao.saveSongListe(songListe);
				System.out.println("createSongListe: Received songListe: " + songListe.toString());
				int newId = songListeDao.addSongListe(songListe);
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(Integer.toString(newId));
				return Response.created(uriBuilder.build()).build();
			}
			else
				return Response.status(Response.Status.NOT_FOUND).entity("User with UserId " + currentUser + "has no Permisson for User" + userId).build();
		}
	
	
	
}
