package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import de.htwBerlin.ai.kbe.bean.Song;

@Singleton
public class DBSongsDAO implements SongsDAO {

    private EntityManagerFactory emf;

    @Inject
    public DBSongsDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public Song findSongById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Song entity = null;
        try {
            entity = em.find(Song.class, id);
        } finally {
            em.close();
        }
        return entity;
    }

    @Override
    public Collection<Song> findAllSongs() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Song> query = em.createQuery("SELECT s FROM Song s", Song.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Integer saveSong(Song song) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // MUST set the song in every address
//            for (Address a:song.getAddressSet()) {
//                a.setSong(song);
//            }
            em.persist(song);
            transaction.commit();
            return song.getId();
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
    public void deleteSong(Integer id) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Song song = null;
        try {
            song = em.find(Song.class, id);
            if (song != null) {
                System.out.println("Deleting: " + song.getId() + " with Title: " + song.getTitle());
                transaction.begin();
                em.remove(song);
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
    
    @Override
    public void initSongs() { 	
    	try {
//    		Collection<Song> songs = findAllSongs();
//        	for (Song s : songs) {
//        		deleteSong(s.getId());
//        	}
    		// Leon /home/s0552107/Uni/Sose18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/songs.json
    		// Emil /Users/emilovic/Documents/htw/git/SoSe18KBERLIN/songsRX/src/main/resources/songs.json
			List<Song> initSongs = Parser.readJSONToSongs("/home/s0552107/Uni/Sose18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/songs.json");
			for (Song s : initSongs) {
				Song song = new Song.Builder(s.getTitle())
						.artist(s.getArtist())
						.album(s.getAlbum())
						.released(s.getReleased()).build();
				saveSong(song);
			}
				
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
    }
}