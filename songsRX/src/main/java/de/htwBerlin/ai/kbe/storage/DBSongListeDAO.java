package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


import java.util.List;


import javax.inject.Singleton;

import de.htwBerlin.ai.kbe.bean.SongListe;

@Singleton
public class DBSongListeDAO implements SongListeDAO {
	private EntityManagerFactory emf;

    @Inject
    public DBSongListeDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public SongListe findSongListeById(Integer id) {
        EntityManager em = emf.createEntityManager();
        SongListe entity = null;
        try {
            entity = em.find(SongListe.class, id);
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public Collection<SongListe> findAllSongListen() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SongListe> query = em.createQuery("SELECT sl FROM SongListe sl", SongListe.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Integer saveSongListe(SongListe songListe) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // MUST set the song in every address
//            for (Address a:song.getAddressSet()) {
//                a.setSong(song);
//            }
            em.persist(songListe);
            transaction.commit();
            return songListe.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding song: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not persist entity: " + e.toString());
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteSongListe(Integer id) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        SongListe songListe = null;
        try {
            songListe = em.find(SongListe.class, id);
            if (songListe != null) {
                System.out.println("Deleting: " + songListe.getId() + " with ID: " + songListe.getId());
                transaction.begin();
                em.remove(songListe);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing song: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not remove entity: " + e.toString());
        } finally {
            em.close();
        }
    }

}
