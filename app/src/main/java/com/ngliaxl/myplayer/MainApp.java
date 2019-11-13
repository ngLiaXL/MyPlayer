/**
 *
 */
package com.ngliaxl.myplayer;

import android.app.Application;
import android.content.Context;

import com.ngliaxl.myplayer.loader.GlideImageLoader;
import com.ngliaxl.myplayer.loader.SafetyImageLoader;


public class MainApp extends Application {

    private static Context sCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        sCtx = getApplicationContext();

        SafetyImageLoader.getInstance().init(new GlideImageLoader());

    }

    public static Context getContext() {
        return sCtx;
    }


}
