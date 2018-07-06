package de.htwBerlin.ai.kbe.example;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.storage.Parser;


public class SongsTesterMain {


    public static void main(String[] args) {
        
        // Datei persistence.xml wird automatisch eingelesen, beim Start der Applikation
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("songs-PU");

        // EntityManager bietet Zugriff auf Datenbank
        EntityManager em = factory.createEntityManager();
        
        try {
            em.getTransaction().begin();
            // Create: neuen Contact anlegen
            
            try {

    			List<Song> initSongs = Parser.readJSONToSongs("/home/s0552107/Uni/Sose18/kbe/SoSe18KBERLIN/songsRX/src/main/resources/songs.json");
    			for (Song s : initSongs) {
    					Song song = new Song.Builder(s.getTitle())
    							.artist(s.getArtist())
    							.album(s.getAlbum())
    							.released(s.getReleased()).build();
    				em.persist(song);
    			}
    		}
    		catch (Exception e) {
    			System.out.println(e.getMessage());
    			
    		}
            // Wir persistieren nur contact, 
            // wegen cascade=CascadeType.ALL werden auch address1, address 2 persistiert
    		System.out.println("aaa");
            
            // commit transaction
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // EntityManager nach Datenbankaktionen wieder freigeben
            em.close();
            // Freigabe am Ende der Applikation
            factory.close();
        }
    }


}