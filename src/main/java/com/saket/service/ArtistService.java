package com.saket.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.saket.model.Album;
import com.saket.model.Artist;
import com.saket.repository.ArtistDaoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ArtistService{
    private Logger logger;
    private ArtistDaoImpl artistDao;

    @Autowired
    public ArtistService(Logger logger, ArtistDaoImpl artistDao){
        this.logger = logger;
        this.artistDao = artistDao;
    }

    public boolean save(Artist artist){
        return artistDao.save(artist);
    }

    public boolean update(Artist artist){
        return artistDao.update(artist);
    }

    public boolean deleteByName(String name){
        return artistDao.deleteArtistByName(name);
    }

    public Artist getArtistByName(String name){
        return artistDao.getArtistByName(name);
    }

    public List<Object[]> getArtistAndAlbums(String name){
        return artistDao.getArtistAndAlbums(name);
    }

    public List<Object[]> getArtistAndAlbumsAndStocks(String name){
        return artistDao.getArtistAndAlbumsAndStocks(name);
    }

    public List<Artist> getArtistList(){
        return artistDao.getArtistList();
    }

    public List<Artist> getArtistListWithChildren(){
        return artistDao.getArtistListWithChildren();
    }

}
