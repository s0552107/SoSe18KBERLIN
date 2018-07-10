package de.htwBerlin.ai.kbe.services;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.storage.SongListeDAO;
import de.htwBerlin.ai.kbe.storage.SongsDAO;

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
	
	
	
}
