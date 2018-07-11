package de.htwBerlin.ai.kbe.storage;


import java.util.Collection;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.bean.User;

public interface UsersDAO {

    /**
     * Retrieves a contact
     * 
     * @param id
     * @return
     */
    public User findUserByUserId(String aUserId);

    /**
     * Retrieves all contacts
     * 
     * @return
     */
    public Collection<User> findAllUsers();

    /**
     * Save a new contact
     * 
     * @param song
     * @return the Id of the new contacts
     */
    public Integer saveUser(User user);

   // public Integer updateUserSongListe(SongListe songListe, User user);
    
    /**
     * Deletes the contact for the provided id
     * @param id
     */
    public void deleteUser(Integer id);

    

  
}