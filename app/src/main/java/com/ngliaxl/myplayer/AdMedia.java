package com.ngliaxl.myplayer;

public class AdMedia {

    private int id;
    private String sid;
    private String url;
    private String path;
    private int times;
    private String type;
    private int status;
    private String duration;
    private String beginTime;
    private String endTime;
    private String priority;
    private String downloadTime;
    private String md5;


    public AdMedia copy() {
        AdMedia that = new AdMedia();
        that.setId(id);
        that.setSid(sid);
        that.setUrl(url);
        that.setPath(path);
        that.setTimes(times);
        that.setType(type);
        that.setStatus(status);
        that.setDuration(duration);
        that.setBeginTime(beginTime);
        that.setEndTime(endTime);
        that.setPriority(priority);
        that.setDownloadTime(downloadTime);
        that.setMd5(md5);

        return that;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}