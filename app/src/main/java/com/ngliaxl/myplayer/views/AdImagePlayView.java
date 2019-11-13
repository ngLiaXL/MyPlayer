package com.ngliaxl.myplayer.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.ngliaxl.myplayer.R;
import com.ngliaxl.myplayer.loader.SafetyImageLoader;


@SuppressLint("ViewConstructor")
public class AdImagePlayView extends AdMediaPlayView {

    private static final int INTERVAL = 10000;
    private Handler mHandler;
    private ImageView adImageView;

    public AdImagePlayView(@NonNull Context context, MediaPlayListener mediaPlayListener) {
        super(context, mediaPlayListener);
    }


    @Override
    protected void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_ad_image_play, this);
        adImageView = findViewById(R.id.ad_image);
        mHandler = new Handler();

    }

    private Runnable mPlayFinishedCallback = new Runnable() {
        @Override
        public void run() {
            if (!isAttachedToWindow()) {
                return;
            }
            if (mediaPlayListener != null) {
                mediaPlayListener.onMediaPlayFinished(AdImagePlayView.this);
            }
        }
    };

    @Override
    public void start() {
        SafetyImageLoader.getInstance().display(adImageView, adMedia.getPath());
        mHandler.postDelayed(mPlayFinishedCallback, INTERVAL);

    }

    @Override
    public void stop() {
        mHandler.removeCallbacksAndMessages(null);
    }


}
