package com.example.yantwin.compiedemo.model;

import java.util.List;


public class PlaylistResponse {
    private List<Playlist> Playlists;

/*
  a class which contains the response from retrofit call , a list of playlists
 */
    public PlaylistResponse() {
    }
    public List<Playlist> getPlaylists() {
        return Playlists;
    }




}
