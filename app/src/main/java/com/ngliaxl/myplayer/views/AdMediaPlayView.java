package com.ngliaxl.myplayer.views;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ngliaxl.myplayer.AdMedia;


public abstract class AdMediaPlayView extends AdBaseView {

    protected MediaPlayListener mediaPlayListener;

    protected AdMedia adMedia;

    public AdMediaPlayView(@NonNull Context context, MediaPlayListener mediaPlayListener) {
        super(context);
        this.mediaPlayListener = mediaPlayListener;
        init();
    }


    public void setAdMedia(AdMedia adMedia) {
        this.adMedia = adMedia;
    }


    protected abstract void init();

    public abstract void start();

    public abstract void stop();


    public void setMediaPlayListener(MediaPlayListener mediaPlayListener) {
        this.mediaPlayListener = mediaPlayListener;
    }

    public interface MediaPlayListener {
        void onMediaPlayFinished(AdMediaPlayView playLayout);
        void onMediaPlayErrors(AdMedia adMedia);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

}
