package de.htwBerlin.ai.kbe.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;

import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.storage.SongListeDAO;
import de.htwBerlin.ai.kbe.storage.SongsDAO;

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
import de.htwBerlin.ai.kbe.storage.SongBook;
import org.glassfish.jersey.inject.hk2.RequestContext;
import de.htwBerlin.ai.kbe.storage.SongsDAO;
import javax.inject.Inject;


@Path("/userId/{userId}/songLists")


public class SongListenWebService {
	
	private SongListeDAO songListeDao;
	
	@Inject
	public SongListenWebService (SongListeDAO dao) {
		this.songListeDao = dao;
			
	}
	
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
		public Response createSongListe(SongListe songListe) {
			
			
			
			
			System.out.println("createSongListe: Received songListe: " + songListe.toString());
	        int newId = songListeDao.saveSongListe(songListe);
	        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
	        uriBuilder.path(Integer.toString(newId));
	        return Response.created(uriBuilder.build()).build();
		}
	
	
	
}
