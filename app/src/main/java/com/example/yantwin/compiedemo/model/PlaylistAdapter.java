package com.example.yantwin.compiedemo.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.bumptech.glide.Glide;
import com.example.yantwin.compiedemo.R;

import java.util.List;

public class PlaylistAdapter extends ExpandableRecyclerAdapter<Playlist,Video,PlaylistAdapter.PlaylistHolder , PlaylistAdapter.VideoHolder> {
/*
the playlist adapter , extends expandlable adapter that way it can have parent and child
 */
    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<Playlist> mPlaylistList;
    private PlaylistClickListener mListener;
    public PlaylistAdapter(Context context, @NonNull List<Playlist> parentItemList, PlaylistClickListener listener) {
        super(parentItemList);
        mContext=context;
        mListener = listener;
        mInflater = LayoutInflater.from(context);
        mPlaylistList = parentItemList;
    }


    @NonNull
    @Override
    public PlaylistAdapter.PlaylistHolder  onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item_playlist, parentViewGroup, false);
        return new PlaylistAdapter.PlaylistHolder(view);    }

    @NonNull
    @Override
    public PlaylistAdapter.VideoHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item_video, childViewGroup, false);
        return new PlaylistAdapter.VideoHolder(view);    }

    @Override
    public void onBindParentViewHolder(@NonNull final PlaylistHolder parentViewHolder, final int parentPosition, @NonNull Playlist parent) {
        Playlist playlist = parent;
        parentViewHolder.mName.setText(playlist.getmName());
        parentViewHolder.mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(parentPosition, parentViewHolder.isExpanded());

            }
        });


    }

    @Override
    public void onBindChildViewHolder(@NonNull VideoHolder childViewHolder, final int parentPosition, final int childPosition, @NonNull Video child) {
        final Video video = child;
        childViewHolder.mName.setText(video.getTitle());
        Glide.with(mContext)
                .load(video.getThumb()) // Uri of the picture
                .into(childViewHolder.mPhoto);
        childViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(childPosition, video.getLink().substring(1+video.getLink().indexOf("=")));

            }
        });
    }

    public class PlaylistHolder extends ParentViewHolder {
// parent view holder , the playlist
        private TextView mName;
        public PlaylistHolder (View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.PlaylistName);
            itemView.setOnClickListener(this);
        }


    }
    public class VideoHolder extends ChildViewHolder {
//child view holder , the video
        private ImageView mPhoto;
        private TextView mName;
        private LinearLayout container;

        public VideoHolder (View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.PlaylistPhoto);
            mName = (TextView) itemView.findViewById(R.id.PlaylistName);
            container = (LinearLayout) itemView.findViewById(R.id.container_view);

        }


    }
    public interface PlaylistClickListener {
// on click interface
        void onClick(int position, boolean isExpanded); //onparentclick
        void onClick(int position, String URL);  //onchildclick
    }
}
