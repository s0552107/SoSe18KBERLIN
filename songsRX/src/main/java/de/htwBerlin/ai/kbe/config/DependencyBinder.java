package de.htwBerlin.ai.kbe.config;


import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.storage.SongsDAO;
import de.htwBerlin.ai.kbe.storage.DBSongsDAO;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind (Persistence
                .createEntityManagerFactory("songs-PU"))
                .to(EntityManagerFactory.class);
        bind(DBSongsDAO.class)
        .to(SongsDAO.class)
        .in(Singleton.class);
    }
}