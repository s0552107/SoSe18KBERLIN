package de.htwBerlin.ai.kbe.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.htwBerlin.ai.kbe.bean.Song;

public class SongsClient {

	private static Client client = ClientBuilder.newClient();
	private static WebTarget baseTarget = client.target("http://localhost:8080/songsRX/rest/songs");

	public static void main(String[] args) {
		listSongs();
		getSong(1);
		createSongs();
		listSongs();

		// Song song = getSong(1);
		// song.setLastName("NEWLASTNAME");
		// updateSong(song);
		// deleteSongById(3, 4);
	}

	private static void listSongs() {
		System.out.println("------- Getting all songs:");
		
		// Antwort als JSON-String 
		String response = baseTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println("JSON-Songs received: " + response);
				
		// Antwort gleich eine List von Song-POJOs einlesen lassen
		List<Song> songs = baseTarget.request().get(new GenericType<List<Song>>() {});
		songs.forEach(System.out::println);
	}

	private static Song getSong(long id) {
		System.out.printf("------- Getting song with id: %s\n", id);
		
		// Antwort als XML-String 
		String response = baseTarget.path(Long.toString(id)).request(MediaType.APPLICATION_XML).get(String.class);
		System.out.println("XML-Song received: " + response);
		
		// Antwort gleich in das Song-POJO einlesen lassen
		Song song = baseTarget.path(Long.toString(id)).request(MediaType.APPLICATION_XML).get(Song.class);
		System.out.println("Song retrieved: " + song);
		return song;
	}

	private static void createSongs() {
		System.out.println("------- Creating or posting songs:");
		for (int i = 1; i <= 5; i++) {
			Song song = new Song();
			song.setTitle("FirstName " + i);
			song.setArtist("LastName " + i);
			Response response;
			Entity<Song> entity;
			if (i%2 == 0) {
				entity = Entity.xml(song);
			} else {
				entity = Entity.json(song);
			}
			System.out.println("Posting new song: " + entity.toString());
			response = baseTarget.request().post(entity);
			System.out.println("Created song: " 
					+ response.getStatus() + " id =" + response.readEntity(String.class));
		}
	}

//	private static void updateSong(Song song) {
//		System.out.printf("------- Updating (putting) a song with id: %s\n", song.getId());
//		Response response = baseTarget.path(Long.toString(song.getId())).request().put(Entity.xml(song));
//		System.out.println("Updated song: " + response.getStatus());
//	}
//
//	private static void deleteSongById(int... ids) {
//		System.out.printf("------- Deleting songs: %s\n", Arrays.toString(ids));
//		for (int id : ids) {
//			WebTarget target = baseTarget.path(Long.toString(id));
//			Response response = target.request().delete();
//			System.out.println("Deleted song: " + response.getStatus());
//		}
//	}
}