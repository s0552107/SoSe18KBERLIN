package de.htw.ai.kbe.songsServlet;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "song")
public class Song {
	private Integer id;
	private String titel;
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
		private String titel;
		private String artist;
		private String album;
		private Integer released;

		public Builder(Integer id, String titel) {
			this.id = id;
			this.titel = titel;
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
		this.titel = builder.titel;
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
	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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
		return "Song [id=" + id + ", firstName=" + titel + ", lastName=" + artist + ", mobile=" + album
				+ ", emailAddress=" + released +"]";
	}
}