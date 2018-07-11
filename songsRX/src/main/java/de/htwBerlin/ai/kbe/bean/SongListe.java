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
	private User owner;
	private boolean privateFlag;
	
	//@ManyToMany(cascade = CascadeType.PERSIST)
	//@JoinTable(name ="sinsl",
	//	joinColumns = {@JoinColumn( name ="slid", referencedColumnName = "id")},
	//	inverseJoinColumns = {@JoinColumn( name="sid", referencedColumnName = "id")})
	private List<Song> songList;


	

	// needed for JAXB
	public SongListe() {
	}

	// Example of a builder:
	public static class Builder {
		// required parameter2018

		private User owner;
		private boolean privateFlag;
		private List<Song> songList;

		public Builder(User owner) {
			
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



	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid") //oder id
	public void setOwner(User owner) {

		this.owner = owner;
	}

	public boolean getPrivateFlag() {
		return privateFlag;
	}

	public void setPrivateFlag(boolean privat) {
		this.privateFlag = privat;
	}
	
	
	@ManyToMany (fetch = FetchType.EAGER)
	public List<Song> getSongList() {
		return songList;
	}

	public void setSonglist(List<Song> songList) {
		this.songList = songList;
		//if(songList != null) {
		//	this.songList.forEach(s->s.);
		//}


	}



	


	

//	@Override
//	public String toString() {
//		return "Song [id=" + id + ", firstName=" + title + ", lastName=" + artist + ", mobile=" + album
//				+ ", emailAddress=" + released +"]";
//	}
}
