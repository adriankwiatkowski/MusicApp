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

public class FavouriteFragment extends Fragment {

    private FavouriteSongsAdapter mAdapter;
    private ArrayList<Song> songs;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private Parcelable mLayoutManagerViewState;
    private String LAYOUT_MANAGER_STATE_KEY = "layoutManagerStateKey";
    private String ARRAY_LIST_STATE_KEY = "arrayListStateKey";

    public FavouriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        mRecyclerView = rootView.findViewById(R.id.favourite_recycler);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        if (savedInstanceState != null && savedInstanceState.containsKey(ARRAY_LIST_STATE_KEY)
                && savedInstanceState.containsKey(LAYOUT_MANAGER_STATE_KEY)) {
            mLayoutManagerViewState = savedInstanceState.getParcelable(LAYOUT_MANAGER_STATE_KEY);
            mLinearLayoutManager.onRestoreInstanceState(mLayoutManagerViewState);
            songs = savedInstanceState.getParcelableArrayList(ARRAY_LIST_STATE_KEY);
        }

        if (songs == null) {
            Log.i(getTag(), "Favourite no songs yet");
            songs = new ArrayList<>();
        }

        mAdapter = new FavouriteSongsAdapter(getActivity(), songs);
        if (mLinearLayoutManager != null) {
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        } else {
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mLayoutManagerViewState = mLinearLayoutManager.onSaveInstanceState();
        outState.putParcelable(LAYOUT_MANAGER_STATE_KEY, mLayoutManagerViewState);
        outState.putParcelableArrayList(ARRAY_LIST_STATE_KEY, songs);
    }

    public void addData(Song song) {
        songs.add(song);
        mAdapter.notifyDataSetChanged();
    }

    public void deleteData(Song song) {
        songs.remove(song);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
    }
}
