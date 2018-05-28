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
//		
//		Song Pokerface = new Song.Builder(1, "Pokerface")
//				.artist("Lady Gaga")
//				.album("Pokerface")
//				.released(2014).build();		
//		storage.put(Pokerface.getId(), Pokerface);
//		Song WeWillRockYou = new Song.Builder(2, "We will rock you")
//				.artist("Queen")
//				.album("Queens")
//				.released(1992).build();		
//		storage.put(WeWillRockYou.getId(), WeWillRockYou);	
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