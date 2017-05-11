package com.example.yantwin.compiedemo.dependencies;


import com.example.yantwin.compiedemo.service.PlaylistService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ApiModule {

    @Provides
    @CustomScope
    PlaylistService providePlaylistService(Retrofit retrofit) {
        return retrofit.create(PlaylistService.class);
    }
}
