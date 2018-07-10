package de.htwBerlin.ai.kbe.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@XmlRootElement(name = "songListe")
@Entity
@Table(name = "Songliste")
public class SongListe {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private User owner;
	private boolean privateFlag;
	
	@OneToMany(mappedBy="songListe",
			cascade=CascadeType.ALL,
			orphanRemoval=true,
			fetch = FetchType.EAGER)
	private List<Song> songList;
	

	// needed for JAXB
	public SongListe() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter2018
		private Integer id;
		private User owner;
		private boolean privateFlag;
		private List<Song> songList;

		public Builder( User owner) {
			
			this.owner = owner;
		}

		public Builder privateFlag(boolean val) {
			privateFlag = val;
			return this;
		}

		public Builder songList(List<Song> val) {
			songList = val;
			return this;
		}

		public SongListe build() {
			return new SongListe(this);
		}
	}

	private SongListe(Builder builder) {
		this.owner = builder.owner;
		this.privateFlag = builder.privateFlag;
		this.songList = builder.songList;

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

	public boolean getPrivatFlag() {
		return privateFlag;
	}

	public void setPrivatFlag(boolean privat) {
		this.privateFlag = privat;
	}

	public List<Song> getSongList() {
		return songList;
	}

	public void setSonglist(List<Song> songList) {
		this.songList = songList;
	}



	

//	@Override
//	public String toString() {
//		return "Song [id=" + id + ", firstName=" + title + ", lastName=" + artist + ", mobile=" + album
//				+ ", emailAddress=" + released +"]";
//	}
}
