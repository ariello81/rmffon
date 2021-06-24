package pl.ryzykowski.rmffon.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Song {

    @JsonAlias("song_id")
    private long songId;

    private String artist;

    private String title;

    private Album album;

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
