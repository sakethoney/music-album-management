package com.saket.repository;

import java.util.List;

import com.saket.model.Artist;

public interface ArtistDao {
    boolean save(Artist artist);
    boolean update(Artist artist);
    boolean delete(Artist artist);
    List<Artist> getArtistList();
}
