package com.ngliaxl.myplayer;


import com.ngliaxl.myplayer.persistence.datasource.DataSource;
import com.ngliaxl.myplayer.persistence.tables.MediaT;

import java.util.ArrayList;
import java.util.List;

public class AdDataSource extends DataSource {


    public List<AdMedia> getMedias(List<Integer> status, @MediaT.MediaCategory.CATEGORY int category) {
        List<MediaT> medias = database().mediaDao().getMedias(status, category);
        List<AdMedia> result = new ArrayList<>();
        if (medias != null && !medias.isEmpty()) {
            for (MediaT t : medias) {
                result.add(convert(t));
            }
        }
        return result;
    }

    public int updateMedia(@MediaT.MediaStatus.Status int status, int id) {
        return database().mediaDao().update(status, id);
    }


    public void delete(int id) {
        database().mediaDao().delete(id);
    }

    public List<MediaT> getGroupMedias() {
        return database().mediaDao().getGroupMedias();
    }


    private AdMedia convert(MediaT from) {
        AdMedia to = new AdMedia();
        to.setId(from.getId());
        to.setSid(from.getSid());
        to.setUrl(from.getUrl());
        to.setPath(from.getPath());
        to.setTimes(from.getTimes());
        to.setType(from.getType());
        to.setStatus(from.getStatus());

        to.setDuration(from.getDuration());
        to.setBeginTime(from.getBeginTime());
        to.setEndTime(from.getEndTime());
        to.setPriority(from.getPriority());
        to.setDownloadTime(from.getDownloadTime());
        to.setMd5(from.getMd5());

        return to;
    }

    private MediaT revert(AdMedia from) {
        MediaT to = new MediaT();
        to.setId(from.getId());
        to.setSid(from.getSid());
        to.setUrl(from.getUrl());
        to.setPath(from.getPath());
        to.setTimes(from.getTimes());
        to.setType(from.getType());
        to.setStatus(from.getStatus());

        to.setDuration(from.getDuration());
        to.setBeginTime(from.getBeginTime());
        to.setEndTime(from.getEndTime());
        to.setPriority(from.getPriority());
        to.setDownloadTime(from.getDownloadTime());
        to.setMd5(from.getMd5());


        return to;
    }
}
