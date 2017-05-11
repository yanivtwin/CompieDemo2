package com.example.yantwin.compiedemo.service;


import com.example.yantwin.compiedemo.model.PlaylistResponse;

import retrofit2.http.GET;
import rx.Observable;


public interface PlaylistService {   //retrofit call with rx observable

    @GET("/hiring/youtube-api.json")
    Observable<PlaylistResponse> getPlaylists();
}
