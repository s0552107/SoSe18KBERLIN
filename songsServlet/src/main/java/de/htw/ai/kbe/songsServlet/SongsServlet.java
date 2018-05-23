package de.htw.ai.kbe.songsServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SongsServlet extends HttpServlet {
	
	Songs songs = Songs.getInstance();
	
	public void init(ServletConfig servletConfig) throws ServletException {
		try {
			List<Song> initSongs = Parser.readJSONToSongs("~/Uni/Sose18/kbe/songs.json");
			for (Song s : initSongs)
				  songs.addSong(s);
			
		}
		catch (Exception e) {

			
		}	
		//TEST
		Song bob = new Song.Builder(23123, "lol")
				.artist("lo")
				.album("a")
				.released(1212).build();
		Songs.getInstance().addSong(bob);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String responseString = "";
		response.setContentType("text/plain");
		try (PrintWriter out = response.getWriter()) {
			for (Song s : songs.getAllSongs()) {
				out.println(songs.getAllSongs().size());
				responseString += s.toString();
			}
			out.println(responseString);
		}
		
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		ServletInputStream inputStream = request.getInputStream();
		byte[] inBytes = IOUtils.toByteArray(inputStream);
		try (PrintWriter out = response.getWriter()) {
			out.println(new String(inBytes));
		}
	}
	
	
	
}
