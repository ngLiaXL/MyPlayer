package com.ngliaxl.myplayer.views;

import android.media.MediaPlayer;

import com.ngliaxl.myplayer.AdMedia;


public class AdMediaPlayer extends MediaPlayer {

    private AdMedia media;

    public AdMedia getMedia() {
        return media;
    }

    public void setMedia(AdMedia media) {
        this.media = media;
    }

}
