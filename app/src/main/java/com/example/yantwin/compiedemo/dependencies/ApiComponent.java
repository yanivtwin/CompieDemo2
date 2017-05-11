package com.example.yantwin.compiedemo.dependencies;


import com.example.yantwin.compiedemo.ui.MainActivity;

import dagger.Component;


@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity activity);
}
