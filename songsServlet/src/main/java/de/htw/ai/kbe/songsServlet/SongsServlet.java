package de.htw.ai.kbe.songsServlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
			List<Song> initSongs = Parser.readJSONToSongs("/home/s0552107/Uni/Sose18/kbe/songs.json");
			for (Song s : initSongs)
				  songs.addSong(s);			
		}
		catch (Exception e) {	

		}	
		//TEST
//		Song BadRomance = new Song.Builder(3, "Bad Romance")
//				.artist("Lady Gaga")
//				.album("Album 2")
//				.released(2012).build();
//		Songs.getInstance().addSong(BadRomance);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Enumeration enumeration = request.getParameterNames();
		String parameterName = "";
		while(enumeration.hasMoreElements())
			parameterName = (String) enumeration.nextElement();
		if (parameterName.equals("all")){
			String responseString = "";
			response.setContentType("text/plain");
			try (PrintWriter out = response.getWriter()) {
				for (Song s : songs.getAllSongs()) {
					responseString += s.toString();
			
				}
				out.println(responseString);	
			}			
		}
		else if (parameterName.equals("songId")){
			try (PrintWriter out = response.getWriter()) {
			
			out.println(songs.getSong(Integer.valueOf(request.getParameter("songId"))).toString());
			}
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
