package com.springlab.jpatest.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSTRUMENT")
public class Instrument {

	@Id
	@Column(name = "INSTRUMENT_ID")
	private String instrumentId;

	@ManyToMany
	@JoinTable(name = "SINGER_INSTRUMENT",
		joinColumns = { @JoinColumn(name = "INSTRUMENT_ID") },
		inverseJoinColumns = { @JoinColumn(name = "SINGER_ID") })
	private Set<Singer> singers = new HashSet<>();

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public Set<Singer> getSingers() {
		return singers;
	}

	public void setSingers(Set<Singer> singers) {
		this.singers = singers;
	}

	@Override
	public String toString() {
		return "Instrument [instrumentId=" + instrumentId + "]";
	}
		
}
