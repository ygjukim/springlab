package com.springlab.hibernatetest.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "SINGER")
@NamedQueries({
	@NamedQuery(name = "Singer.findAllWithAlbums",
		query = "select distinct s from Singer s " +
				"left join fetch s.albums a " +
				"left join fetch s.instruments i"),
	@NamedQuery(name = "Singer.findById",
		query = "select distinct s from Singer s " +
				"left join fetch s.albums a " +
				"left join fetch s.instruments i " +
				"where s.id = :id")
})
public class Singer extends AbstractEntity {

	public static final String FIND_SINGER_BY_ID = "Singer.findById";
	public static final String FIND_ALL_WITH_ALBUMS = "Singer.findAllWithAlbums";
	
	@Column(name ="FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Album> albums;
	
	@ManyToMany
	@JoinTable(name = "SINGER_INSTRUMENT",
			joinColumns = { @JoinColumn(name = "SINGER_ID") },
			inverseJoinColumns = { @JoinColumn(name = "INSTRUMENT_ID") } )
	private Set<Instrument> instruments;

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

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

    public boolean addAlbum(Album album) {
        album.setSinger(this);
        return albums.add(album);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
    }
    
	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

    public boolean addInstrument(Instrument instrument) {
        return instruments.add(instrument);
    }
    
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return "Singer [firstName=" + firstName + ", lastName=" + lastName + 
				", birthDate=" + sdf.format(birthDate) + ", id=" + id + "]";
	}
	
    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Singer singer = (Singer) o;
        if (firstName != null ? !firstName.equals(singer.firstName) : singer.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(singer.lastName) : singer.lastName != null)
            return false;
        return birthDate != null ? birthDate.equals(singer.birthDate) : singer.birthDate == null;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
	
}
