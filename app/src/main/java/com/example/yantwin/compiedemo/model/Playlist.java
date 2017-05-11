package com.example.yantwin.compiedemo.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;


public class Playlist implements Parent<Video> {

    /*
    the parent class will contain a name , and a list of children (Video)
     */
    private List<Video> ListItems;
    private String ListTitle;

    public Playlist() {
    }

    public Playlist(String name, List<Video> children) {
        ListTitle = name;
        ListItems = children;
    }



    public String getmName() {
        return ListTitle;
    }

    public void setmName(String mName) {
        this.ListTitle = mName;
    }

    @Override
    public List<Video> getChildList() {
        return ListItems;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
