package de.htwBerlin.ai.kbe.bean;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@XmlRootElement(name = "song")
@Entity 
@Table(name="Song")
public class Song {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;
	

	// needed for JAXB
	public Song() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter
		private Integer id;
		private String title;
		private String artist;
		private String album;
		private Integer released;

		public Builder(Integer id, String title) {
			this.id = id;
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
		this.id = builder.id;
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
