package com.example.yantwin.compiedemo.service;


import com.example.yantwin.compiedemo.model.PlaylistResponse;

import rx.Observable;


public interface PlaylistViewInterface {   // interface for MainActivity

    void onCompleted();

    void onError(String message);

    void onPlaylists(PlaylistResponse PlaylistResponses);

    Observable<PlaylistResponse> getPlaylists();
}
