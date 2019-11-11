package com.ngliaxl.myplayer.loader;

import android.widget.ImageView;

import androidx.annotation.NonNull;

public class SafetyImageLoader {

    private static final SafetyImageLoader INSTANCE = new SafetyImageLoader();
    private ImageLoader mLoader;

    private SafetyImageLoader() {
    }

    public static SafetyImageLoader getInstance() {
        return INSTANCE;
    }

    public void init(@NonNull ImageLoader loader) {
        this.mLoader = loader;
    }

    public void display(@NonNull ImageView img, @NonNull String absPath) {
        if (ensureLoader()) {
            throw new IllegalStateException("init method should be called first");
        }
        mLoader.display(img, absPath);
    }


    public ImageLoader getLoader() {
        return mLoader;
    }

    private boolean ensureLoader() {
        return mLoader == null;
    }
}