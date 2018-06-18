package com.example.fragment.subcontechs;


import java.io.Serializable;


public class Audio implements Serializable {

    private String data;
    private String title;
    private String album;
    private String artist;
    private String thumbnail;

    public Audio(String data, String title, String album, String artist, String thumbnail) {
        this.data = data;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.thumbnail = thumbnail;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMusicThumbnailAlbum() {
        return thumbnail;
    }

    public void setMusicThumbnailAlbume(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
