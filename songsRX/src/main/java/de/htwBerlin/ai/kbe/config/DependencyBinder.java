package de.htwBerlin.ai.kbe.config;


import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.storage.SongsDAO;
import de.htwBerlin.ai.kbe.storage.DBSongListeDAO;
import de.htwBerlin.ai.kbe.storage.DBSongsDAO;
import de.htwBerlin.ai.kbe.storage.UsersDAO;
import de.htwBerlin.ai.kbe.storage.DBUsersDAO;
import de.htwBerlin.ai.kbe.storage.SongListeDAO;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind (Persistence
                .createEntityManagerFactory("songs-PU"))
                .to(EntityManagerFactory.class);
        bind(DBSongsDAO.class)
        .to(SongsDAO.class)
        .in(Singleton.class);
        
        bind(DBUsersDAO.class)
        .to(UsersDAO.class)
        .in(Singleton.class);
        
        bind(DBSongListeDAO.class)
        .to(SongListeDAO.class)
        .in(Singleton.class);
    }
    
}