package com.saket.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "artist")
public class Artist extends Model implements Serializable {
    //public interface Brief{};
    //public interface Full extends Brief{};
    //public interface WithChildren{};

    //@JsonView({Brief.class, Full.class, WithChildren.class})
    @Column(name = "name")
    private String name;

    //@JsonView({Full.class, WithChildren.class})
    @Column(name = "start_year")
    private int startYear;

    //@JsonView({Full.class, WithChildren.class})
    @Column(name = "end_year")
    private int endYear;

    //@JsonView({Full.class, WithChildren.class})
    @Column
    private String description;

    //@JsonView({WithChildren.class})
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Album> albums;

    public Artist(int id, String name, int startYear, int endYear, String description) {
        this.id = id;
        this.name = name;
        this.startYear = startYear;
        this.endYear = endYear;
        this.description = description;
    }

    public Artist(String name, int start_year, int end_year, String description) {
        this.name = name;
        this.startYear = start_year;
        this.endYear = end_year;
        this.description = description;
    }

    public Artist(String name){
        this.name = name;
    }

    public Artist(){
        this.id = 0;
        this.name = "NULL";
        this.startYear = 0;
        this.endYear = 0;
        this.description = "NULL";
    }

    public Artist(Artist artist){
        this.id = artist.getId();
        this.name = artist.getName();
        this.startYear = artist.getStartYear();
        this.endYear = artist.getEndYear();
        this.description = artist.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Album> getAlbums() {
        try{
            albums.size();
        } catch (Exception e){
            return null;
        }
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        for(Album album : albums){
            if (album.getArtist() == null) album.setArtist(this);
        }
        this.albums = albums;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof Artist)) return false;
        Artist a = (Artist) obj;
        if(this.getId() == a.getId() &&
            this.getName() == a.getName() &&
            this.getStartYear() == a.getStartYear() &&
            this.getEndYear() == a.getEndYear() &&
            this.getDescription() == a.getDescription()) return true;
        return super.equals(obj);
    }
}
