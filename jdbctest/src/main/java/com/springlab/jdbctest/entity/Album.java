package com.springlab.jdbctest.entity;

import java.util.Date;

public class Album {

	private Long id;
	private Long singerId;
	private String title;
	private Date releaseDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSingerId() {
		return singerId;
	}

	public void setSingerId(Long singerId) {
		this.singerId = singerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", singerId=" + singerId + ", title=" + title + ", releaseDate=" + releaseDate + "]";
	}
	
}
