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

public class SongsServlet extends HttpServlet {
	

	//private Songs songs;

	public void init(ServletConfig servletConfig) throws ServletException {
	//	this.Songs =  ReadJSONToSongs (servletConfig.getInitParameter("fileName"));
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
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
