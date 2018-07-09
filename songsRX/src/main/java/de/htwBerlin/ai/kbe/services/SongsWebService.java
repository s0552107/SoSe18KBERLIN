package de.htwBerlin.ai.kbe.services;

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

// URL fuer diesen Service ist: http://localhost:8080/songsRX/rest/songs
@Path("/songs")
public class SongsWebService {
	private SongsDAO songsDao;
	
	@Inject
	public SongsWebService(SongsDAO dao) {
		this.songsDao = dao;
		
	}
	//GET http://localhost:8080/songsRX/rest/songs
	//Returns all songs
	
	@GET
	@Permisson
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Collection<Song> getAllSongs() {
//		return SongBook.getInstance().getAllSongs();
//		System.out.println("getAllSongs: Returning all songs!");
		songsDao.initSongs();		
		return songsDao.findAllSongs();
	}

	//GET http://localhost:8080/songsRX/rest/songs/1
	//Returns: 200 and song with id 1
	//Returns: 404 on provided id not found
	@GET
	@Permisson
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getSong(@PathParam("id") Integer id) {
		Song song = SongBook.getInstance().getSong(id);
		if (song != null) {
			System.out.println("getSong: Returning Song for id " + id);
			return Response.ok(song).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No Song found with id " + id).build();
		}
	}

	// POST http://localhost:8080/helloJAXRS/rest/songs with song in payload
	// Returns: Status Code 201 and the new id of the song in the payload 
	// (temp. solution)
	//
	// Besser: Status Code 201 und URI fuer den neuen Eintrag im http-header 'Location' zurueckschicken, also:
	// Location: /helloJAXRS/rest/songs/neueID
	// Aber dazu brauchen wir "dependency injection", also spaeter

	// return Response.created(uriInfo.getAbsolutePath()+<newId>).build();
    @Context UriInfo uriInfo;
	@POST
    @Permisson
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSong(Song song) {
		System.out.println("createSong: Received song: " + song.toString());
        int newId = SongBook.getInstance().addSong(song);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(newId));
        return Response.created(uriBuilder.build()).build();
	}
	
	//PUT http://localhost:8080/helloJAXRS/rest/songs/1 with updated song in payload
    //Returns 204 on successful update
	//Returns 404 on song with provided id not found
	//Returns 400 on id in URL does not match id in song
	//todo
	@PUT
    @Permisson
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/{id}")
    public Response updateSong(@PathParam("id") Integer id, Song song) {
		boolean bool = SongBook.getInstance().updateSong(id, song);
		if (bool)
			return Response.status(Response.Status.NO_CONTENT).entity("Song" + id + " succesfully updated").build();
		else
			return Response.status(Response.Status.NOT_FOUND).entity("Song " + id + " doesn’t exist").build();
    }
	
	//DELETE http://localhost:8080/helloJAXRS/rest/songs/1
    //Returns 204 on successful delete
	//Returns 404 on provided id not found
	@DELETE
    @Permisson
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		boolean bool = SongBook.getInstance().deleteSong(id);
		if(bool)
			return Response.status(Response.Status.NO_CONTENT).entity("Song" + id + " deleted").build();
		else
			return Response.status(Response.Status.NOT_FOUND).entity("Song " + id + " doesn’t exist").build();
	}
}