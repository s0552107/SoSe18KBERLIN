package de.htwBerlin.ai.kbe.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwBerlin.ai.kbe.bean.Song;
import de.htwBerlin.ai.kbe.bean.SongListe;
import de.htwBerlin.ai.kbe.bean.User;


public class Parser {
	
	 static void writeSongsToXML(SongBook songs, String filename) throws JAXBException, FileNotFoundException, IOException {
		JAXBContext context = JAXBContext.newInstance(SongBook.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filename))) {
			marshaller.marshal(songs, os);
		}
		
	}

	// Reads a list of songs from an XML-file into Songs.java
	 static SongBook readXMLToSongBook(String filename) throws JAXBException, FileNotFoundException, IOException {
		JAXBContext context = JAXBContext.newInstance(SongBook.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			return (SongBook) unmarshaller.unmarshal(is);
		}
	}

	// Reads a list of SongBook from a JSON-file into List<Song>
	@SuppressWarnings("unchecked")
	 public static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>(){});
		}
	}
	
	@SuppressWarnings("unchecked")
	 public static List<User> readJSONToUsers(String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			return (List<User>) objectMapper.readValue(is, new TypeReference<List<User>>(){});
		}
	}
	
	@SuppressWarnings("unchecked")
	 public static List<SongListe> readJSONToSongListen(String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			return (List<SongListe>) objectMapper.readValue(is, new TypeReference<List<SongListe>>(){});
		}
	}

	// Write a List<Song> to a JSON-file
	 static void writeSongsToJSON(List<Song> SongBook, String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filename))) {
			objectMapper.writeValue(os, SongBook);
		}
	}

}