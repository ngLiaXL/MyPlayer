package com.ngliaxl.myplayer.loader;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class GlideImageLoader implements ImageLoader {


    private DrawableCrossFadeFactory drawableCrossFadeFactory =
            new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();

    @Override
    public void display(@NonNull ImageView img, @NonNull String absPath) {
        String path = "file://" + absPath;
        try {
            Glide.with(img.getContext()).load(path)
                    .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory)).into(img);
        } catch (IllegalArgumentException ignore) {
        }
    }
}
