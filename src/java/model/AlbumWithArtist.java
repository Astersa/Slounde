package model;

/**
 * DTO class that extends Album to include artist name from joined queries
 */
public class AlbumWithArtist extends Album {
    private String artistName;

    public AlbumWithArtist() {
        super();
    }

    public AlbumWithArtist(Album album, String artistName) {
        super(album.getId(), album.getName(), album.getArtistId(), album.getLikes(), album.getAlbumUrl());
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}