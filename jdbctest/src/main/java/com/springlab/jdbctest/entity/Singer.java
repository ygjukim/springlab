package com.springlab.jdbctest.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Singer {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Album> albums;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDay) {
		this.birthDate = birthDay;
	}
	
	public boolean addAlbum(Album album) {
		if (albums == null) {
			albums = new ArrayList<Album>();
		}
		else {
			if (albums.contains(album)) {
				return false;
			}
		}
		albums.add(album);
		return true;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDay=" + birthDate
				+ "]";
	}

}
