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
	public static void main(String[] args) {
//		OurSong song = new OurSong(1, "A new title", "MeMyself and I", "A new Album", 2017);
//		filename = "outJAXBOneSong.xml";
//		try {
//			writeSongToXML(song, filename);
//			OurSong readSong = readXMLToSong(filename);
//			System.out.println("READ xml: songid = " + readSong.getId());
//		} catch (Exception e) { // not good, but saves space!
//			e.printStackTrace();
//		}
		
//		try {
//			Songs readSongs = readXMLToSongs("songs.xml");
//			System.out.println("READ xml: # of songs = " + readSongs.getSong().size());
//          filename = "outJAXBManySongs.xml";
//			writeSongsToXML(readSongs, filename);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
		List<Song> readSongs = readJSONToSongs("src/main/resources/songs.json");		
		System.out.println("READ JSON: # of songs = " + readSongs.get(0));
        
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

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
