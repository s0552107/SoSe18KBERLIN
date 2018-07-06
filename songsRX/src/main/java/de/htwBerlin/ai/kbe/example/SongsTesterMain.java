package de.htwBerlin.ai.kbe.example;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import de.htwBerlin.ai.kbe.bean.Song;


public class SongsTesterMain {


    public static void main(String[] args) {
        
        // Datei persistence.xml wird automatisch eingelesen, beim Start der Applikation
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("songs-PU");

        // EntityManager bietet Zugriff auf Datenbank
        EntityManager em = factory.createEntityManager();
        
        try {
            em.getTransaction().begin();
            // Create: neuen Contact anlegen
            
    		Song bob = new Song.Builder("a").build();
            // Wir persistieren nur contact, 
            // wegen cascade=CascadeType.ALL werden auch address1, address 2 persistiert
    		System.out.println("aaa");
            em.persist(bob);;
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