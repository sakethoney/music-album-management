package com.saket.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "album")
public class Album extends Model implements Serializable {
    //public interface Brief{};
    //public interface Full extends Artist.Brief {};
    //public interface WithChildren{};

    //@JsonView({Brief.class, Full.class, WithChildren.class, Artist.WithChildren.class})
    @Column(name = "name")
    private String name;

    //@JsonView({Full.class, WithChildren.class, Artist.WithChildren.class})
    @Column(name = "release_year")
    private int releaseYear;

    //@JsonView({Brief.class, Full.class, WithChildren.class, Artist.WithChildren.class})
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    //select * from album al left join artist art on al.artist_serial_num = art.serial_num;
    private Artist artist;

    //@JsonView({Full.class, WithChildren.class, Artist.WithChildren.class})
    @Column
    private String genre;

    //@JsonView({Full.class, WithChildren.class, Artist.WithChildren.class})
    @Column(name = "description")
    private String description;


    public Album(int id, String name, int releaseYear, Artist artist, String genre, String description) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
    }

    public Album(String name, int release_year, Artist artist, String genre, String description) {
        this.name = name;
        this.releaseYear = release_year;
        this.artist = artist;
        this.genre = genre;
        this.description = description;
    }

    public Album(){
        this.id = 0;
        this.name = "NULL";
        this.releaseYear = 0;
        this.artist = null;
        this.genre = "NULL";
        this.description = "NULL";
    }

    public Album(Album album){
        this.id = album.getId();
        this.name = album.getName();
        this.releaseYear = album.getReleaseYear();
        this.artist = album.getArtist();
        this.genre = album.getGenre();
        this.description = album.getDescription();
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseYear(int release_year) {
        this.releaseYear = release_year;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Album)) return false;
        Album o = (Album) obj;
        if(this.getId() == o.getId() &&
            this.getReleaseYear() == o.getReleaseYear() &&
            this.getArtist() == o.getArtist() &&
            this.getName() == o.getName() &&
            this.getGenre() == o.getGenre() &&
            this.getDescription() == o.getDescription()) return true;
        return super.equals(obj);
    }
}
