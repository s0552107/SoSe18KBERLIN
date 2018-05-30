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
	

	
	private void sendAsJson(
			HttpServletResponse response, 
			Object obj) throws IOException {
		
			ObjectMapper mapper = new ObjectMapper();
			
			response.setContentType("application/json");
			
			String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			     
			PrintWriter out = response.getWriter();
			  
			out.print(res);
			out.flush();
	}
	
	
	public void init(ServletConfig servletConfig) throws ServletException {
		try {
			List<Song> initSongs = Parser.readJSONToSongs("/home/s0552107/Uni/Sose18/kbe/songs.json");
//			List<Song> initSongs = Parser.readJSONToSongs("/home/s0549218/Downloads/songs.json");
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
		
		String accept = request.getHeader("Accept");	
		
		if (accept.contains("application/json"))
		{
			
			Enumeration<String> enumeration = request.getParameterNames();
			String parameterName = "";
			while(enumeration.hasMoreElements())
				parameterName = (String) enumeration.nextElement();
			if (parameterName.equals("all")){
				String responseString = "";
				response.setContentType("application/json");
				try (PrintWriter out = response.getWriter()) {
					for (Song s : songs.getAllSongs()) {
						//Parser.writeSongsToJSON(songs, "out.json");
						sendAsJson(response, s);

					}

				}			
			}
			else if (parameterName.equals("songId")){
				try (PrintWriter out = response.getWriter()) {
				
				out.println(songs.getSong(Integer.valueOf(request.getParameter("songId"))).toString());
				}
			}
		}
		else if (accept.contains("application/xml"))
		{
			Enumeration<String> enumeration = request.getParameterNames();
			String parameterName = "";
			while(enumeration.hasMoreElements())
				parameterName = (String) enumeration.nextElement();
			if (parameterName.equals("all")){
				String responseString = "";
				response.setContentType("application/xml");
				try (PrintWriter out = response.getWriter()) {
					for (Song s : songs.getAllSongs()) {
					//	Parser.writeSongsToXML(songs, "out.xml");
						out.print(s.toString());

					}

				}			
			}
			else if (parameterName.equals("songId")){
				try (PrintWriter out = response.getWriter()) {
				
				out.println(songs.getSong(Integer.valueOf(request.getParameter("songId"))).toString());
				}
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
