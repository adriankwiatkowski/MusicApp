package com.example.android.example;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class FavouriteSongsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<Song> songs;
    private SongAdapter.DataPassListener mCallback;

    public FavouriteSongsAdapter(Context context, ArrayList<Song> favouriteSongs) {
        this.mContext = context;
        this.songs = favouriteSongs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(getItem(position).getText());
        final Song currentItem = getItem(position);


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.deleteData(currentItem);
            }
        });

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), currentItem.getMediaPlayerResourceId());
                mediaPlayer.start();
            }
        });
    }

    private Song getItem(int position) {
        return songs.get(position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        try {
            mCallback = (SongAdapter.DataPassListener) mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString() + " must implement DataPassListener");
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mCallback = null;
    }
}
