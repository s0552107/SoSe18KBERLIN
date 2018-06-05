package de.htwBerlin.ai.kbe.storage;

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

import de.htwBerlin.ai.kbe.bean.Song;

public class SongBook {

	private static Map<Integer,Song> storage;
	private static SongBook instance = null;
	
	private SongBook() {
		storage = new HashMap<Integer,Song>();
		initSomeSongs();
	}
	
	public synchronized static SongBook getInstance() {
		if (instance == null) {
			instance = new SongBook();
		}
		return instance;
	}
	
	
	
	private static void initSomeSongs() {
		
//		Song bob = new Song.Builder(23123, "lol")
//				.artist("lo")
//				.album("a")
//				.released(1212).build();
//		
//		storage.put(bob.getId(), bob);
		try {
			List<Song> initSongs = Parser.readJSONToSongs("resources/songs.json");
			for (Song s : initSongs)
				storage.put(s.getId(), s);
		}
		catch (Exception e) {
			
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
