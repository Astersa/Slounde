package model;

/**
 * DTO class that extends Song to include artist name from joined queries
 */
public class SongWithArtist extends Song {
    private String artistName;

    public SongWithArtist() {
        super();
    }

    public SongWithArtist(Song song, String artistName) {
        super(song.getId(), song.getName(), song.getStreams(), song.getLikes(),
                song.getSongUrl(), song.getThumbnailUrl(), song.getAlbumId(), song.getArtistId());
        this.artistName = artistName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}