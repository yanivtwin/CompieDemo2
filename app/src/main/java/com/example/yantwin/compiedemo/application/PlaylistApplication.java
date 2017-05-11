package com.example.yantwin.compiedemo.application;

import android.app.Application;

import com.example.yantwin.compiedemo.dependencies.ApiComponent;
import com.example.yantwin.compiedemo.dependencies.DaggerApiComponent;
import com.example.yantwin.compiedemo.dependencies.DaggerNetworkComponent;
import com.example.yantwin.compiedemo.dependencies.NetworkComponent;
import com.example.yantwin.compiedemo.dependencies.NetworkModule;
import com.example.yantwin.compiedemo.model.Constant;


public class PlaylistApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))

                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
