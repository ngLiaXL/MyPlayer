package com.ngliaxl.myplayer.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.ngliaxl.myplayer.AdMedia;
import com.ngliaxl.myplayer.R;
import com.ngliaxl.myplayer.persistence.tables.MediaT;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint("ViewConstructor")
public class AdVideoPlayView extends AdMediaPlayView implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private AdMediaPlayer safetyMediaPlayer;
    private volatile boolean surfaceCreated;
    private ExecutorService singleThreadExecutor;

    public AdVideoPlayView(@NonNull Context context, MediaPlayListener mediaPlayListener) {
        super(context, mediaPlayListener);
    }


    @Override
    protected void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_ad_video_play, this);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceView.getHolder().addCallback(this);
        singleThreadExecutor = Executors.newSingleThreadExecutor();
    }


   /*

    进入此页面要判断是否有和要求的视频
    private void createSurface() {
        SurfaceView surfaceView = new SurfaceView(getContext());
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        addView(surfaceView);
    }
*/


    /**
     * Wait for surfaceCreated
     */
    @Override
    public void start() {
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                play();
            }
        });


    }


    private void play() {
        synchronized (this) {
            while (!surfaceCreated) {
                try {
                    wait();
                } catch (InterruptedException ignored) {
                }
            }
        }

        safetyMediaPlayer.setMedia(adMedia);
        try {
            safetyMediaPlayer.setDataSource(adMedia.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            safetyMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initSafetyMediaPlayer() {
        if (safetyMediaPlayer == null) {
            safetyMediaPlayer = new AdMediaPlayer();
            safetyMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    onVideoPlayCompleted(mp);
                }
            });

            safetyMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    AdMediaPlayer mediaPlayer = (AdMediaPlayer) mp;
                    mediaPlayer.getMedia().setStatus(MediaT.MediaStatus.STATUS_ERROR);
                    onVideoPlayCompleted(mp);
                    return true;
                }
            });

            safetyMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    try {
                        safetyMediaPlayer.start();
                    } catch (Exception e) {
                        onVideoPlayCompleted(mp);
                    }
                }
            });
        }

        safetyMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        safetyMediaPlayer.setDisplay(surfaceView.getHolder());


    }

    private void onVideoPlayCompleted(MediaPlayer mp) {
        AdMediaPlayer mediaPlayer = (AdMediaPlayer) mp;
        if (mediaPlayer != null) {
            AdMedia media = mediaPlayer.getMedia();
            if (media.getStatus() == MediaT.MediaStatus.STATUS_ERROR) {
                if (mediaPlayListener != null) {
                    mediaPlayListener.onMediaPlayErrors(adMedia);
                }
            }
            mediaPlayer.reset();
            if (mediaPlayListener != null) {
                mediaPlayListener.onMediaPlayFinished(this);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initSafetyMediaPlayer();
        synchronized (this) {
            surfaceCreated = true;
            notifyAll();
        }
        //start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        surfaceCreated = false;

    }


    @Override
    public void stop() {
        if (safetyMediaPlayer != null) {
            try {
                safetyMediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            safetyMediaPlayer = null;
        }
    }

}
