package model;


/**
 * DTO class that extends Song to include artist name from joined queries
 */
public class SongDTO extends Song {

    private String artistName;
    private boolean like;
    private String albumName;
    private String likeAt;

    public SongDTO() {
        super();
    }

    public SongDTO(Song song, String artistName, boolean like, String albumName, String likeAt) {
        super(song.getId(), song.getName(), song.getStreams(), song.getLikes(),
                song.getSongUrl(), song.getThumbnailUrl(), song.getAlbumId(), song.getArtistId());
        this.artistName = artistName;
        this.like = like;
        this.albumName = albumName;
        this.likeAt = likeAt;
    }

    public String getLikeAt() {
        return likeAt;
    }

    public void setLikeAt(String likeAt) {
        this.likeAt = likeAt;
    }
    
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
    
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "SongDTO{" + "artistName=" + artistName + ", like=" + like + ", albumName=" + albumName + ", likeAt=" + likeAt + '}';
    }
    
}
