package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.bean.SongListe;

public interface SongsDAO {

    /**
     * Retrieves a contact
     * 
     * @param id
     * @return
     */
    public Song findSongById(Integer id);

    /**
     * Retrieves all contacts
     * 
     * @return
     */
    public Collection<Song> findAllSongs();

    /**
     * Save a new contact
     * 
     * @param song
     * @return the Id of the new contacts
     */
    public Integer saveSong(Song song);
    
    /**
     * Deletes the contact for the provided id
     * @param id
     */
    public void deleteSong(Integer id);
    
    public boolean updateSong(Integer id, Song song);
    
   
    
    /**
     * init songs
     */
    public void initSongs();
    
}