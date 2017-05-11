package com.example.yantwin.compiedemo.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.yantwin.compiedemo.R;
import com.example.yantwin.compiedemo.application.PlaylistApplication;
import com.example.yantwin.compiedemo.base.PlaylistPresenter;
import com.example.yantwin.compiedemo.model.DividerItemDecoration;
import com.example.yantwin.compiedemo.model.PlaylistAdapter;
import com.example.yantwin.compiedemo.model.PlaylistResponse;
import com.example.yantwin.compiedemo.service.PlaylistService;
import com.example.yantwin.compiedemo.service.PlaylistViewInterface;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import rx.Observable;

import static com.example.yantwin.compiedemo.R.id.recyclerView;

/*
 *       The List Activity
 */
public class MainActivity extends AppCompatActivity implements PlaylistViewInterface, PlaylistAdapter.PlaylistClickListener {

    @Inject
    PlaylistService mService;            // the Retrofit call to get the Json

    private PlaylistPresenter mPresenter; //the presenter for this activity

    @Bind(recyclerView)   //butterknife binding
    RecyclerView mRecyclerView;
    private PlaylistAdapter mAdapter;  //list adapter

    private ProgressDialog mDialog;
    public static final String EXTRA_MESSAGE = "URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolveDependency();

        ButterKnife.bind(MainActivity.this);

        configViews();
        setPresenter();
    }

    private void setPresenter() {    // set the presenter and starts fetching data
        mPresenter = new PlaylistPresenter(MainActivity.this);
        mPresenter.onCreate();

        mPresenter.onResume();
        mPresenter.fetchPlaylists();
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setIndeterminate(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setTitle("Downloading List");
        mDialog.setMessage("Please wait...");
        mDialog.show();
    }

    private void resolveDependency() {    // resolves the dependencies needed for the the activity
        ((PlaylistApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void configViews() {        //sets the recyclerview's decor and anim
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());

    }


    @Override
    public void onCompleted() {
        mDialog.dismiss();
    }  //oncomplete called from the presenter

    @Override
    public void onError(String message) {  //onerror called from presenter
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlaylists(final PlaylistResponse PlaylistResponses) {         //  called by presenter with the list response , sets adapter to recyclerview
        mAdapter = new PlaylistAdapter(this, PlaylistResponses.getPlaylists(),this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public Observable<PlaylistResponse> getPlaylists() {// call to start  the json retrieval
        return mService.getPlaylists();
    }

    @Override
    public void onClick(int position, boolean isExpanded) {  // on parent click , toggle the clicked parent
        if(isExpanded)
            mAdapter.collapseParent(position);
        else
            mAdapter.expandParent(position);

    }

    @Override
    public void onClick(int position, String URL) {  //on child click , moves to next activity and opens youtube
        Log.e("OnCLICK"," position "+position+URL);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, URL);
        startActivity(intent);
    }
}
