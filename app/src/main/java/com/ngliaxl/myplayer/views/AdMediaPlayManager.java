package com.ngliaxl.myplayer.views;

import com.ngliaxl.myplayer.AdDataSource;
import com.ngliaxl.myplayer.AdMedia;
import com.ngliaxl.myplayer.EmptyMediasException;
import com.ngliaxl.myplayer.MainApp;
import com.ngliaxl.myplayer.persistence.datasource.DataSourceFactory;
import com.ngliaxl.myplayer.persistence.tables.MediaT;
import com.ngliaxl.myplayer.utils.FileUtils;
import com.ngliaxl.myplayer.utils.MD5;
import com.ngliaxl.myplayer.utils.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdMediaPlayManager {

    private static final String DEFAULT_IMAGE = "image_default.jpg";

    private LinkedList<AdMedia> mediaCopyList;
    private LinkedList<AdMedia> adMediaList;
    private AdDataSource adDataSource;

    private AdMediaPlayManager() {
        mediaCopyList = new LinkedList<>();
        adMediaList = new LinkedList<>();
        adDataSource = DataSourceFactory.provide(AdDataSource.class);
    }


    private static class Holder {
        private static AdMediaPlayManager INS = new AdMediaPlayManager();
    }

    public static AdMediaPlayManager getInstance() {
        return Holder.INS;
    }


    /**
     * Video
     * Image
     * Video + Image
     *
     * @return
     */
    public List<MediaT> getGroupMedias() {
        // TODO MD5 validate
        return adDataSource.getGroupMedias();
    }

    public int updateMedia(@MediaT.MediaStatus.Status int status, int id) {
        return adDataSource.updateMedia(status, id);

    }


    public AdMedia getNext() {
        AdMedia media = null;
        try {
            media = getMedia();
        } catch (Exception e) {
            if (e instanceof EmptyMediasException) {
                // Play default medias
                media = getDefaultAdMedia();
            }
        }

        File file = new File(media.getPath());
        if (!file.exists() || !file.isFile()) {
            adDataSource.delete(media.getId());
            return getNext();
        }
        return media;
    }


    private AdMedia getDefaultAdMedia() {
        String mediasStorageFile = FileUtils.getMediasStorageFile(DEFAULT_IMAGE);
        File file = new File(mediasStorageFile);
        if (!file.exists()) {
            ResourceUtils.copyAssetsFile(MainApp.getContext(), DEFAULT_IMAGE, file);
        }
        AdMedia media = new AdMedia();
        media.setPath(file.getAbsolutePath());
        media.setType(MediaT.MediaType.TYPE_IMAGE);
        return media;
    }


    /**
     * For push message
     */
    public void clearMediaCache() {
        if (!mediaCopyList.isEmpty()) {
            mediaCopyList.clear();
        }
    }


    private AdMedia getMedia() {
        // Deal with repeat times
        if (!mediaCopyList.isEmpty()) {
            return mediaCopyList.removeFirst();
        }

        if (!adMediaList.isEmpty()) {
            return adMediaList.removeFirst();
        }

        List<AdMedia> medias = adDataSource.getMedias(new ArrayList<Integer>() {
            {
                add(MediaT.MediaStatus.STATUS_DOWNLOADED);
            }
        }, MediaT.MediaCategory.CATEGORY_ADVERTISING);

        if (medias == null || medias.isEmpty()) {
            throw new EmptyMediasException();
        }
        adMediaList.addAll(medias);
        AdMedia adMedia = adMediaList.removeFirst();

        if (adMedia != null && adMedia.getTimes() > 1) {
            for (int i = 1; i < adMedia.getTimes(); i++) {
                mediaCopyList.add(adMedia.copy());
            }
        }

        return adMedia;

    }

    public void displayImage(AdMedia adMedia) {
        if (adMedia == null || adMedia.getPath() == null) {
            return;
        }
        File file = new File(adMedia.getPath());
        boolean localFileExists = file.exists() && file.isFile()
                && MD5.checkMD5(adMedia.getMd5(), file);

        if (!localFileExists) {
            adMedia.setStatus(MediaT.MediaStatus.STATUS_ERROR);
            updateMedia(adMedia.getStatus(), adMedia.getId());
        }

    }


}


