package com.saket.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saket.model.Album;
import com.saket.model.Artist;

import java.util.List;

@Repository
public class AlbumDaoImpl implements AlbumDao {

    @Autowired
    private Logger logger;

    private Connection<Album> albumConnection = new Connection<>();

    @Override
    public boolean save(Album album) {
        //album.setArtist(artistConnection.get);
        return albumConnection.save(album);
    }

    @Override
    public boolean update(Album album) {
        return albumConnection.update(album);
    }

    @Override
    public boolean delete(Album album) {
        return albumConnection.delete(album);
    }

    public boolean deleteAlbumByName(String albumName){
        return delete(getAlbumByName(albumName));
    }

    @Override
    public List<Album> getAlbumList() {
        return albumConnection.getObjectList("Album");
    }

    public List<Album> getAlbumListWithChildren(){
        String hql = "FROM Album as album ";
        return albumConnection.getObjectListWithChildren(hql);
    }

    public Album getAlbumByName(String albumName){
        String hql = "FROM Album as album  " +
                "where lower(album.name) = :param";
        return albumConnection.getObjectByName(hql, albumName);
    }


}
