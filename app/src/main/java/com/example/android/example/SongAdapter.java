package com.example.android.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<Song> songs;
    private String PREFERENCES_NAME = "MyPreferences";
    private DataPassListener mCallback;

    public interface DataPassListener {
        void passData(Song data);

        void deleteData(Song data);
    }

    public SongAdapter(Context context, ArrayList<Song> songs) {
        this.mContext = context;
        this.songs = songs;
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
        final int adapterPosition = holder.getAdapterPosition();

        holder.checkBox.setChecked(getCheckBoxStateFromSp(String.valueOf(holder.getAdapterPosition())));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    mCallback.passData(currentItem);
                    saveCheckBoxInSp(String.valueOf(adapterPosition), holder.checkBox.isChecked());
                } else if (!holder.checkBox.isChecked()) {
                    mCallback.deleteData(currentItem);
                    saveCheckBoxInSp(String.valueOf(adapterPosition), holder.checkBox.isChecked());
                }
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

    private boolean getCheckBoxStateFromSp(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    private void saveCheckBoxInSp(String key, boolean value) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        try {
            mCallback = (DataPassListener) mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString() + " must implement DataPassListener");
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mCallback = null;
    }

    private Song getItem(int position) {
        return songs.get(position);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
