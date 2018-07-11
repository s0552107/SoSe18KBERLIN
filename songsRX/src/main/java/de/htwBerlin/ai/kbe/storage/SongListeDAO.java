package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import de.htwBerlin.ai.kbe.bean.SongListe;

public interface SongListeDAO {
	 /**
     * Retrieves a contact
     * 
     * @param id
     * @return
     */
    public SongListe findSongListeById(Integer id);

    /**
     * Retrieves all contacts
     * 
     * @return
     */
    public Collection<SongListe> findAllSongListen();

    /**
     * Save a new contact
     * 
     * @param songListe
     * @return the Id of the new contacts
     */
    public Integer saveSongListe(SongListe songListe);
    
    /**
     * Deletes the contact for the provided id
     * @param id
     */
    public void deleteSongListe(Integer id);
    
    public Integer addSongListe (SongListe songListe);
    
    public void initSongListen();
	
	
}
