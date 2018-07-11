package de.htwBerlin.ai.kbe.storage;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;


//import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.bean.User;

@Singleton
public class DBUsersDAO implements UsersDAO {

    private EntityManagerFactory emf;

    @Inject
    public DBUsersDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public User findUserByUserId(String aUserId) { 
        EntityManager em = emf.createEntityManager(); try { 
        TypedQuery<User> query = 
        em.createQuery("SELECT u FROM User u where u.userId = :userId",
        User.class);
        query.setParameter("userId", aUserId); 
        User user = query.getSingleResult(); 
        return user;
    } catch (NoResultException nre) { return null; } 
      finally { em.close(); } 
    } 

    @Override
    public Collection<User> findAllUsers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Integer saveUser(User user) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
        //    // MUST set the user in every address
        //    for (SongListe sl:user.getSongListen()) {
         //       sl.setOwner(user);
         //   }
            em.persist(user);
            transaction.commit();
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding user: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not persist entity: " + e.toString());
        } finally {
            em.close();
        }
    }

    /**
    @Override
    public Integer updateUserSongListe(SongListe songListe,  User user) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // MUST set the user in every address
            //for (SongListe sl : user.getSongListen()) {
            //    sl.setOwner(user);
            //}
            songListe.setOwner(user);
            user.addSongListen(songListe);
            transaction.commit();
            return songListe.getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding user: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not update entity: " + e.toString());
        } finally {
            em.close();
        }
    }**/

    @Override
    public void deleteUser(Integer id) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        User user = null;
        try {
            user = em.find(User.class, id);
            if (user != null) {
                System.out.println("Deleting: " + user.getId() + " with User-ID: " + user.getUserId());
                transaction.begin();
                em.remove(user);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error removing user: " + e.getMessage());
            transaction.rollback();
            throw new PersistenceException("Could not remove entity: " + e.toString());
        } finally {
            em.close();
        }
    }

}
