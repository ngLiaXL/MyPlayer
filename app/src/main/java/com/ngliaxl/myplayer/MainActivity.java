package com.ngliaxl.myplayer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ngliaxl.myplayer.persistence.tables.MediaT;
import com.ngliaxl.myplayer.views.AdBaseView;
import com.ngliaxl.myplayer.views.AdImagePlayView;
import com.ngliaxl.myplayer.views.AdMediaPlayManager;
import com.ngliaxl.myplayer.views.AdMediaPlayView;
import com.ngliaxl.myplayer.views.AdVideoPlayView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements AdMediaPlayView.MediaPlayListener {


    private static final String VIEW_VIDEO_PLAY = "view-video-play";
    private static final String VIEW_IMAGE_PLAY = "view-image-play";
    private Map<String, AdBaseView> viewsCacheMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateMediaContent();
    }


    private View getContentView() {
        FrameLayout viewById = findViewById(android.R.id.content);
        return viewById.getChildAt(0);
    }

    private void updateMediaContent() {
        AdMedia adMedia = AdMediaPlayManager.getInstance().getNext();

        AdBaseView baseView = null;

        if (adMedia.getType().equals(MediaT.MediaType.TYPE_VIDEO)) {
            baseView = getDisplayView(VIEW_VIDEO_PLAY);
        } else if (adMedia.getType().equals(MediaT.MediaType.TYPE_IMAGE)) {
            baseView = getDisplayView(VIEW_IMAGE_PLAY);
        }

        if (baseView != getContentView()) {
            setContentView(Objects.requireNonNull(baseView));
        }

        AdMediaPlayView mediaLayout = (AdMediaPlayView) baseView;
        mediaLayout.setAdMedia(adMedia);
        mediaLayout.start();
    }

    private AdBaseView getDisplayView(String viewId) {
        AdBaseView baseView = viewsCacheMap.get(viewId);
        if (baseView == null) {
            if (TextUtils.equals(viewId, VIEW_VIDEO_PLAY)) {
                baseView = new AdVideoPlayView(this, this);
            } else if (TextUtils.equals(viewId, VIEW_IMAGE_PLAY)) {
                baseView = new AdImagePlayView(this, this);
            }
            viewsCacheMap.put(viewId, baseView);
        }
        return baseView;
    }


    @Override
    public void onMediaPlayFinished(AdMediaPlayView playLayout) {
        updateMediaContent();
    }

    @Override
    public void onMediaPlayErrors(AdMedia adMedia) {

    }
}
