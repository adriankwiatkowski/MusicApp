package com.example.android.example;

import android.media.AudioManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SongsFragment extends Fragment {

    private ArrayList<Song> songs;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private String LAYOUT_MANAGER_STATE_KEY = "layoutManagerStateKey";
    private String ARRAY_LIST_STATE_KEY = "arrayListStateKey";

    private Parcelable mLayoutManagerViewState;

    public SongsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mRecyclerView = rootView.findViewById(R.id.recycler);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        if (savedInstanceState != null && savedInstanceState.containsKey(ARRAY_LIST_STATE_KEY)
                && savedInstanceState.containsKey(LAYOUT_MANAGER_STATE_KEY)) {
            mLayoutManagerViewState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE_KEY);
            if (mLinearLayoutManager == null) {
                Log.i(TAG, "LayoutManager was null");
                mLinearLayoutManager.onRestoreInstanceState(mLayoutManagerViewState);
            }
            songs = savedInstanceState.getParcelableArrayList(ARRAY_LIST_STATE_KEY);
        }

        if (songs == null) {
            populateList();
        }

        SongAdapter adapter = new SongAdapter(getActivity(), songs);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save list State
        mLayoutManagerViewState = mLinearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LAYOUT_MANAGER_STATE_KEY, mLayoutManagerViewState);
        outState.putParcelableArrayList(ARRAY_LIST_STATE_KEY, songs);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
    }

    private void populateList() {
        Log.i(getTag(), "Songs no songs yet");
        songs = new ArrayList<>();
        songs.add(new Song("1", R.raw.));
        songs.add(new Song("2", R.raw.));
        songs.add(new Song("3", R.raw.));
        songs.add(new Song("4", R.raw.));
        songs.add(new Song("5", R.raw.));
        songs.add(new Song("6", R.raw.));
        songs.add(new Song("7", R.raw.));
        songs.add(new Song("8", R.raw.));
        songs.add(new Song("9", R.raw.));
        songs.add(new Song("10", R.raw.));
        songs.add(new Song("11", R.raw.));
        songs.add(new Song("12", R.raw.));
        songs.add(new Song("13", R.raw.));
        songs.add(new Song("14", R.raw.));
    }
}
