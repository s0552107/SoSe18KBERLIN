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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.storage.SongBook;


// URL fuer diesen Service ist: http://localhost:8080/songsRX/rest/songs
@Path("/songs")
public class SongsWebService {

	//GET http://localhost:8080/songsRX/rest/songs
	//Returns all songs
	@GET 
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Collection<Song> getAllSongs() {
		System.out.println("getAllSongs: Returning all songs!");
		return SongBook.getInstance().getAllSongs();
	}

	//GET http://localhost:8080/songsRX/rest/songs/1
	//Returns: 200 and song with id 1
	//Returns: 404 on provided id not found
	@GET
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
	// @Context UriInfo uriInfo
	// return Response.created(uriInfo.getAbsolutePath()+<newId>).build(); 
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.TEXT_PLAIN)
	public Response createSong(Song song) {
		System.out.println("createSong: Received song: " + song.toString());

		return Response.status(Response.Status.CREATED).header("Location", song.getId()).build();
	}
	
	//PUT http://localhost:8080/helloJAXRS/rest/songs/1 with updated song in payload
    //Returns 204 on successful update
	//Returns 404 on song with provided id not found
	//Returns 400 on id in URL does not match id in song
	@PUT
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
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		boolean bool = SongBook.getInstance().deleteSong(id);
		if(bool)
			return Response.status(Response.Status.NO_CONTENT).entity("Song" + id + " deleted").build();
		else
			return Response.status(Response.Status.NOT_FOUND).entity("Song " + id + " doesn’t exist").build();
	}
}