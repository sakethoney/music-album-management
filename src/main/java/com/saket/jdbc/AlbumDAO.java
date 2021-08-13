package com.saket.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saket.model.Album;
import com.saket.model.Artist;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String loggerInfo = System.getenv("logging.level.com.saket");
    private MyConnection myConnection = new MyConnection();
    private ResultSet rs = null;

    public List<Album> getAlbums(){
        logger.info("Enter the method getAlbums");
        List<Album> albums = new ArrayList<>();
        try {
            rs = myConnection.connectAndFetchResult("SELECT * FROM album;");
            while(rs.next()){
                albums.add(new Album(
                        rs.getString("name"),
                        rs.getInt("release_year"),
                        (Artist) rs.getObject("artist"),
                        rs.getString("genre"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                myConnection.closeConnection();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("Exit the method getAlbums");
        return albums;
    }

    public void insertAlbums(Album album){
        logger.info("Enter the method insertAlbums");
        String sqlQuery = "INSERT INTO album (name, release_year, artist, genre, description, serial_num) " +
                "VALUES " +
                "('" + album.getName() + "', " +
                album.getReleaseYear() + ", " +
                "'" + album.getArtist() + "', " +
                "'" + album.getGenre() + "', " +
                "'" + album.getDescription() + "');";
        myConnection.doAQuery(sqlQuery);
    }

    public void deleteAlbums(String serialNumber){
        logger.info("Enter the method deleteAlbums");
        String sqlQuery = "DELETE FROM album WHERE serial_num = '" + serialNumber + "';";
        myConnection.doAQuery(sqlQuery);
    }

    public void updateAlbums(String serialNumber, String desc){
        logger.info("Enter the method updateAlbums");
        String sqlQuery = "UPDATE album SET description = '" + desc + "' " +
                "WHERE serial_num = '" + serialNumber + "';";
        myConnection.doAQuery(sqlQuery);
    }
}
