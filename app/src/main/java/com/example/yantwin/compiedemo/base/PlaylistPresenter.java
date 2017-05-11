package com.example.yantwin.compiedemo.base;


import com.example.yantwin.compiedemo.model.PlaylistResponse;
import com.example.yantwin.compiedemo.service.PlaylistViewInterface;

import rx.Observer;


public class PlaylistPresenter extends BasePresenter implements Observer<PlaylistResponse> {

    private PlaylistViewInterface mViewInterface;

    public PlaylistPresenter(PlaylistViewInterface viewInterface) {
        mViewInterface = viewInterface;
    }

    @Override
    public void onCompleted() {// sends the activity  complete notice
        mViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {// sends the activity the error msg
        mViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(PlaylistResponse PlaylistResponses) {  //sends the activity the response list
        mViewInterface.onPlaylists(PlaylistResponses);
    }

    public void fetchPlaylists() {// removes allsubscriptions and  makes a call to get the json
        unSubscribeAll();
        subscribe(mViewInterface.getPlaylists(), PlaylistPresenter.this);
    }
}
