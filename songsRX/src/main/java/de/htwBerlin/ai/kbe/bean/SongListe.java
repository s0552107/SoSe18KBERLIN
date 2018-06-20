package de.htwBerlin.ai.kbe.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "song")
public class SongListe {
	private Integer id;
	private User owner;
	private boolean privat;
	private List<Song> songlist;
	

	// needed for JAXB
	public SongListe() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter
		private Integer id;
		private User owner;
		private boolean privat;
		private List<Song> songlist;

		public Builder(Integer id, User owner) {
			this.id = id;
			this.owner = owner;
		}

		public Builder artist(boolean val) {
			privat = val;
			return this;
		}

		public Builder album(List<Song> val) {
			songlist = val;
			return this;
		}

		public SongListe build() {
			return new SongListe(this);
		}
	}

	private SongListe(Builder builder) {
		this.id = builder.id;
		this.owner = builder.owner;
		this.privat = builder.privat;
		this.songlist = builder.songlist;

	}
	
	// getters and evil setters :-), also needed for JAXB
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean getPrivat() {
		return privat;
	}

	public void setPrivat(boolean privat) {
		this.privat = privat;
	}

	public List<Song> getSonglist() {
		return songlist;
	}

	public void setSonglist(List<Song> songlist) {
		this.songlist = songlist;
	}



	

//	@Override
//	public String toString() {
//		return "Song [id=" + id + ", firstName=" + title + ", lastName=" + artist + ", mobile=" + album
//				+ ", emailAddress=" + released +"]";
//	}
}
