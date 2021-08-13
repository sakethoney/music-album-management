package com.saket.repository;

import java.util.List;

import com.saket.model.Album;

public interface AlbumDao {
    boolean save(Album album);
    boolean update(Album album);
    boolean delete(Album album);
    List<Album> getAlbumList();
}
