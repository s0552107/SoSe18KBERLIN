package de.htw.ai.kbe.songsServlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import  javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Enumeration;


public class SongsServletTest {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletConfig servletConfig;
    enum enumera  {all,songId,nicht;}

    @Test(expected = NullPointerException.class)
    public void noParameter() throws Exception
    {

        SongsServlet songsServlet = new SongsServlet();
        songsServlet.doGet(request,response);
    }
    @Test(expected = IllegalStateException.class)
    public void illegalState() throws Exception {

        SongsServlet songsServlet = new SongsServlet();
        songsServlet.getInitParameter("songs");
        songsServlet.doGet(request, response);
    }
    @Test(expected = NoClassDefFoundError.class)
    public void noClassDefFound() throws Exception {

        SongsServlet songsServlet = new SongsServlet();
        songsServlet.songs = Parser.readXMLToSongs("ich/bin/ausgedacgt");
        songsServlet.doGet(request, response);
    }
}
