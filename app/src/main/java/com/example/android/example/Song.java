package com.example.android.example;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    private String mText;
    private int mMediaPlayerId;

    public Song(String text, int mediaPlayerId) {
        mText = text;
        mMediaPlayerId = mediaPlayerId;
    }

    public String getText() {
        return mText;
    }

    public int getMediaPlayerResourceId() {
        return mMediaPlayerId;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public Song(Parcel in) {
        mText = in.readString();
        mMediaPlayerId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeInt(mMediaPlayerId);
    }
}

/*
public class Song {

    private String mText;
    private int mMediaPlayerId;

    public Song(String text, int mediaPlayerId) {
        mText = text;
        mMediaPlayerId = mediaPlayerId;
    }

    public String getText() {
        return mText;
    }

    public int getMediaPlayerResourceId() {
        return mMediaPlayerId;
    }
}
*/