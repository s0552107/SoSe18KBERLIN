package de.htwBerlin.ai.kbe.bean;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;
import java.util.HashSet;

import de.htwBerlin.ai.kbe.bean.SongListe;

@XmlRootElement(name = "song")
@Entity 
@Table(name="song")
public class Song {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;


	//private Set<SongListe> songListeSet;







	
//	@ManyToOne
//	@JoinColumn(name = "sid")
//	private Set <SongListe> songListe;

	// needed for JAXB
	public Song() {
		
	}
//	public Song(String title, String artist="", String album="", Integer released=0) {
//        this.title = title;
//        this.artist=artist;
//        this.album = album;
//        this.released=released;
//    }
//	public Song(String title, String artist, String album) {
//        this.title = title;
//        this.artist=artist;
//        this.album = album;
//    }
//	public Song(String title, String artist, Integer released) {
//        this.title = title;
//        this.artist=artist;
//        this.released=released;
//    }


	// Example of a builder:
	public static class Builder {
		// required parameter
		private String title;
		private String artist;
		private String album;
		private Integer released;
		private Set<SongListe> songListeSet;
		
		

		public Builder( String title) {
			this.title = title;
		}
		
		 

		public Builder artist(String val) {
			artist = val;
			return this;
		}

		public Builder album(String val) {
			album = val;
			return this;
		}

		public Builder released(Integer val) {
			released = val;
			return this;
		}


		public Song build() {
			return new Song(this);
		}
	}

	private Song(Builder builder) {
		this.title = builder.title;
		this.artist = builder.artist;
		this.album = builder.album;
		this.released = builder.released;

	}
	
	// getters and evil setters :-), also needed for JAXB
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Integer getReleased() {
		return released;
	}

	public void setReleased(Integer released) {
		this.released = released;
	}






	

	@Override
	public String toString() {
		return "Song [id=" + id + ", firstName=" + title + ", lastName=" + artist + ", mobile=" + album
				+ ", emailAddress=" + released +"]";
	}
}
