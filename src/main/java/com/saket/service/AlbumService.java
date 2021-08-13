package com.saket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.saket.model.Album;
import com.saket.model.Artist;
import com.saket.repository.AlbumDaoImpl;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AlbumService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AlbumDaoImpl albumDao;

    @Autowired
    public AlbumService(Logger logger, AlbumDaoImpl albumDao){
        this.logger = logger;
        this.albumDao = albumDao;
    }

    public boolean save(Album album){
        return albumDao.save(album);
    }

    public boolean update(Album album){
        return albumDao.update(album);
    }

    public boolean deleteByName(String name){
        return albumDao.deleteAlbumByName(name);
    }

    public Album getAlbumByName(String name){
        return albumDao.getAlbumByName(name);
    }

    public List<Object[]> getAlbumAndStock(String name){
        return null;
    }

    public List<Album> getAlbumList(){
        return albumDao.getAlbumList();
    }

    public List<Album> getAlbumListWithChildren(){
        return albumDao.getAlbumListWithChildren();
    }

    public Artist searchArtist(String albumName){
        return getAlbumByName(albumName).getArtist();
    }
}
