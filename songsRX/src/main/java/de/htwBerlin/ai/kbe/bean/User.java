package de.htwBerlin.ai.kbe.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import de.htwBerlin.ai.kbe.bean.Song.Builder;

@XmlRootElement(name = "user")
@Entity
@Table(name="users")
public class User {
	
	@Id // kennzeichnet das Identitätsattribut entspricht dem PK (primary key)
    // bedeutet, dass der PK automatisch durch die DB vergeben wird
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	
		private Integer id;
		@Column(name= "userid")
		private String userId;
		private String lastName;
		private String firstName;
		
		

		// needed for JAXB
		public User() {
		}

		// Example of a builder:
		public static class Builder {
			// required parameter
			private Integer id;
			private String userId;
			private String lastName;
			private String firstName;
			

			public Builder(Integer id, String userId) {
				this.id = id;
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
			this.id = builder.id;
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

		public String getAlbum() {
			return firstName;
		}

		public void setAlbum(String firstName) {
			this.firstName = firstName;
		}

	




		

		@Override
		public String toString() {
			return "User [id=" + id + ", userId=" + userId + ", lastName=" + lastName + ", mobile=" + firstName + "]";
		}
}


