package de.htwBerlin.ai.kbe.bean;

import org.hibernate.annotations.Columns;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songListe")
@Entity
@Table(name = "SongListe")
public class SongListe {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer owner;
	private boolean privateFlag;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name ="sinsl",
		joinColumns = {@JoinColumn( name ="slid", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn( name="sid", referencedColumnName = "id")})
	private Set<Song> songList;


	

	// needed for JAXB
	public SongListe() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter2018

		private Integer owner;
		private boolean privateFlag;
		private Set<Song> songList;

		public Builder(Integer owner) {
			
			this.owner = owner;
		}

		public Builder privateFlag(boolean val) {
			privateFlag = val;
			return this;
		}

		public Builder songList(Set<Song> val) {
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

	public Integer getOwner() {
		return owner;
	}




	public void setOwner(Integer owner) {

		this.owner = owner;
	}

	public boolean getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(boolean privat) {
		this.privateFlag = privat;
	}

	public Set<Song> getSongList() {
		return songList;
	}

	public void setSonglist(Set<Song> songList) {
		this.songList = songList;
		//if(songList != null) {
		//	this.songList.forEach(s->s.);
		//}


	}



	public void addSongList(Song song) {
		if(songList == null) {
			songList = new HashSet<>();
		}
		//songList.setContact(this);
		this.songList.add(song);
	}



	

//	@Override
//	public String toString() {
//		return "Song [id=" + id + ", firstName=" + title + ", lastName=" + artist + ", mobile=" + album
//				+ ", emailAddress=" + released +"]";
//	}
}
