package de.htwBerlin.ai.kbe.bean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;


@XmlRootElement(name = "user")
@Entity
@Table(name="Users")
public class User {
	
	@Id // kennzeichnet das Identitätsattribut entspricht dem PK (primary key)
    // bedeutet, dass der PK automatisch durch die DB vergeben wird
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
		private Integer id;
		private String userId;
		private String lastName;
		private String firstName;
		



		

		// needed for JAXB
		public User() {
		}

		// Example of a builder:
		public static class Builder {
			// required parameter

			private String userId;
			private String lastName;
			private String firstName;
			

			public Builder( String userId) {

				this.userId = userId;
			}

			public Builder lastName(String val) {
				lastName = val;
				return this;
			}

			public Builder firstName(String val) {
				firstName = val;
				return this;
			}


			public User build() {
				return new User(this);
			}
		}

		private User(Builder builder) {

			this.userId = builder.userId;
			this.lastName = builder.lastName;
			this.firstName = builder.firstName;
		

		}
		
		// getters and evil setters :-), also needed for JAXB
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}




/**
	public Set<SongListe> getSongListen() {
		if(songListen == null) {
			songListen = new HashSet<>();
		}
		return songListen;
	}

	public void setSongListen(Set<SongListe> songListe) {
		this.songListen = songListe;
		// Works for JSON, but not for XML
		if(songListe != null) {
			this.songListen.forEach(a-> a.setOwner(this));
		}
	}

	public void addSongListen(SongListe songListe) {
		if(songListen == null) {
			songListen = new HashSet<>();
		}
		songListe.setOwner(this);
		this.songListen.add(songListe);
	}**/









	@Override
		public String toString() {
			return "User [id=" + id + ", userId=" + userId + ", lastName=" + lastName + ", mobile=" + firstName + "]";
		}
}


