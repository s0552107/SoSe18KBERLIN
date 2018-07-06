package de.htw.ai.kbe.songsRX;

import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.services.SongsWebService;
import de.htwBerlin.ai.kbe.storage.Parser;
import  org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;


import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;


import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;


public class SongsWebServiceTest extends  JerseyTest {

    /** PUT - TESTS
     * Target-ID (URL) & song.getId() muessen gleich sein sonst --> 404
     * ID muss vorhanden sein, sonst --> 404
     * bei fehlender ID --> 404
     * wenn target-ID == song.getId() && ID im Storage vorhanden --> 204
     */

    //TODO ApplicationConfig (wofuer und wie bekommt man das rote Zeug weg bei den Tests


    @Override
    protected Application configure() {
        return new ResourceConfig(SongsWebService.class);
    }

//
//    @Test
//    public void updateSongJsonPayload(){
//        Song song = new Song.Builder(9, "Jetzt geht die Party richtig los ")
//                .artist("De Randfichten")
//                .album("Heja Ho, de Randfichten sei do")
//                .released(2005).build();
//
//        Response output = target("songs/" + song.getId())
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_JSON));
//        assertEquals("Should return status 204", 204, output.getStatus());
//    }
//
//    @Test
//    public void updateSongXMLPayload(){
//        Song song = new Song.Builder(9, "Jetzt geht die Party richtig los ")
//                .artist("De Randfichten")
//                .album("Heja Ho, de Randfichten sei do")
//                .released(2005).build();
//
//        Response output = target("songs/" + song.getId())
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_XML));
//        assertEquals("Song" + song.getId() + " succesfully updated", 204, output.getStatus());
//    }
//
//    @Test
//    public void updateSongJsonWithUnknownID(){
//        Song song = new Song.Builder(99, "Jetzt geht die Party richtig los ")
//                .artist("De Randfichten")
//                .album("Heja Ho, de Randfichten sei do")
//                .released(2005).build();
//
//        Response output = target("songs/" + song.getId())
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_JSON));
//        assertEquals("Should return status 404", 404, output.getStatus());
//    }
//
//    @Test
//    public void updateSongXMLWithUnknownID(){
//        Song song = new Song.Builder(99, "Jetzt geht die Party richtig los ")
//                .artist("De Randfichten")
//                .album("Heja Ho, de Randfichten sei do")
//                .released(2005).build();
//
//        Response output = target("songs/" + song.getId())
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_XML));
//        assertEquals("Should return status 404", 404, output.getStatus());
//    }
//
//    @Test
//    public void updateSongwithoutID(){
//        Song song = new Song();
//        song.setArtist("De Randfichten");
//        song.setAlbum("Heja Ho, de Randfichten sei do");
//        song.setReleased(2005);
//        Response output = target("songs/9")
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_XML));
//        assertEquals("Should return status 204", 404, output.getStatus());
//    }
//
//    @Test
//    public void updateSongwithDifferentIdTargetSong(){
//        Song song = new Song.Builder(99, "Jetzt geht die Party richtig los ")
//                .artist("De Randfichten")
//                .album("Heja Ho, de Randfichten sei do")
//                .released(2005).build();
//
//        Response output = target("songs/9")
//                .request()
//                .put(Entity.entity(song, MediaType.APPLICATION_XML));
//        assertEquals("Should return status 204", 404, output.getStatus());
//    }

}
