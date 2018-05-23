package de.htw.ai.kbe.songsServlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Songs {
	public static void main(String[] args) {		
		try {
		List<Song> readSongs = readJSONToSongs("src/main/resources/songs.json");		
		System.out.println("READ JSON: # of songs = " + readSongs.get(0));
        
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	private static Map<Integer,Song> storage;
	private static Songs instance = null;
	
	private Songs() {
		storage = new HashMap<Integer,Song>();
		initSomeSongs();
	}
	
	public synchronized static Songs getInstance() {
		if (instance == null) {
			instance = new Songs();
		}
		return instance;
	}
	
	
	
	private static void initSomeSongs() {
		
		Song bob = new Song.Builder(23123, "lol")
				.artist("lo")
				.album("a")
				.released(1212).build();
		
		storage.put(bob.getId(), bob);
		
		try {

			List<Song> readSongs = readJSONToSongs("src/main/resources/songs.json");
			
			Song bitte = new  Song.Builder(readSongs.get(1).getId(),readSongs.get(1).getTitel()).build();
			storage.put(bitte.getId(), bitte);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		

	}
	
	@SuppressWarnings("unchecked")
	static List<Song> readJSONToSongs (String filename) throws FileNotFoundException, IOException {
//		Song ccc = new Song.Builder(999, "kekaasdada").build();
//		storage.put(ccc.getId(), ccc);
		ObjectMapper objectMapper = new ObjectMapper();
		
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
//			Song ddd = new Song.Builder(88, "lololollolol").build();
//			storage.put(ddd.getId(), ddd);
			return (List<Song>) objectMapper.readValue(is, List.class);
		}
	}
	
	public Song getSong(Integer id) {
		return storage.get(id);
	}
	
	public Collection<Song> getAllSongs() {
		return storage.values();
	}
	
	public Integer addSong(Song song) {
		// Fuer Beleg 3: Das koennen Sie im Songs' store NICHT machen!
		song.setId((int)storage.keySet().stream().count() + 1);
		storage.put(song.getId(), song);
		return song.getId();
	}
	
	// returns true (success), when song exists in map and was updated
	// returns false, when song does not exist in map
	public boolean updateSong(Song song) {
		throw new UnsupportedOperationException("updateSong: not yet implemented");
	}
	
	// returns deleted song
	public Song deleteSong(Integer id) {
		throw new UnsupportedOperationException("deleteSong: not yet implemented");
	}
}