/**
 *
 */
package com.ngliaxl.myplayer.utils;

import android.os.Environment;

import com.ngliaxl.myplayer.MainApp;

import java.io.File;
import java.util.Objects;


public class ExternalStorageUtils {

    static final String MEIDA_DRI = "my-player";

    private static boolean mExternalStorageAvailable = false;
    private static boolean mExternalStorageWriteable = false;

    /**
     *
     */
    private ExternalStorageUtils() {
    }

    private static void updateExternalStorageState() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }

    public static File getAppExternalStorageRootFolder() {
        updateExternalStorageState();
        if (mExternalStorageAvailable && mExternalStorageWriteable) {
            return Environment.getExternalStoragePublicDirectory(MEIDA_DRI);
        }
        return MainApp.getContext().getExternalFilesDir(MEIDA_DRI);
    }

    private static String getAppExternalStorageFolder(String parentName) {
        File appExtraRootFile = new File(getAppExternalStorageRootFolder() + File.separator + parentName);
        if (!appExtraRootFile.exists()) {
            if (!makeDir(appExtraRootFile)) {
                return null;
            }
        }
        return appExtraRootFile.getAbsolutePath();
    }

    public static String getAppExternalStorageFile(String fileName, String parentName) {
        File appExtraRootFile = new File(Objects.requireNonNull(getAppExternalStorageFolder(parentName)));
        return appExtraRootFile.getAbsolutePath() + File.separator + fileName;
    }


    public static String getAppPirvateStorage(String fileName, String parentName) {
        File appExtraRootFile = new File(MainApp.getContext().getFilesDir().getAbsolutePath()
                + File.separator + parentName);
        if (!appExtraRootFile.exists()) {
            if (!makeDir(appExtraRootFile)) {
                return null;
            }
        }
        return appExtraRootFile.getAbsolutePath() + File.separator + fileName;
    }


    private static boolean makeDir(File dir) {
        if (!dir.getParentFile().exists()) {
            makeDir(dir.getParentFile());
        }
        return dir.mkdir();
    }

}
