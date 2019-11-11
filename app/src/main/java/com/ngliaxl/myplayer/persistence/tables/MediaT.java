
package com.ngliaxl.myplayer.persistence.tables;


import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "media")
public class MediaT {

    public interface MediaStatus {
        /**
         * 素材损坏
         */
        int STATUS_ERROR = -1;
        /**
         * 待下载
         */
        int STATUS_TO_DOWNLOAD = 0;
        /**
         * 已下载
         */
        int STATUS_DOWNLOADED = 1;


        @IntDef({STATUS_ERROR, STATUS_TO_DOWNLOAD, STATUS_DOWNLOADED})
        @interface Status {
        }
    }

    public interface MediaType {
        String TYPE_VIDEO = "vdo";
        String TYPE_IMAGE = "img";

        @StringDef({TYPE_VIDEO, TYPE_IMAGE})
        @interface TYPE {
        }
    }


    public interface MediaCategory {
        int CATEGORY_ADVERTISING = 0;
        int CATEGORY_RESCUE = 1;

        @IntDef({CATEGORY_ADVERTISING, CATEGORY_RESCUE})
        @interface CATEGORY {
        }
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    /**
     * 服务端id
     */
    @ColumnInfo(name = "sid")
    private String sid;
    /**
     * 服务端存储位置
     */
    @ColumnInfo(name = "url")
    private String url;
    /**
     * 本地存储位置
     */
    @ColumnInfo(name = "path")
    private String path;
    /**
     * 播放次数
     */
    @ColumnInfo(name = "play_times")
    private int times;
    /**
     *
     */
    @ColumnInfo(name = "type")
    @MediaType.TYPE
    private String type;

    @ColumnInfo(name = "category")
    @MediaCategory.CATEGORY
    private int category;
    /**
     * 状态
     */
    @ColumnInfo(name = "status")
    @MediaStatus.Status
    private int status;
    /**
     * 播放时长
     */
    @ColumnInfo(name = "duration")
    private String duration;
    /**
     * 开始播放时间
     */
    @ColumnInfo(name = "begin_time")
    private String beginTime;
    /**
     * 结束播放时间
     */
    @ColumnInfo(name = "end_time")
    private String endTime;
    /**
     * 优先级
     */
    @ColumnInfo(name = "priority")
    private String priority;
    /**
     * 素材下载时间-目的限制下载
     */
    @ColumnInfo(name = "download_time")
    private String downloadTime;

    @ColumnInfo(name = "md5")
    private String md5;


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

    public void setType( @MediaType.TYPE String type) {
        this.type = type;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(@MediaCategory.CATEGORY int category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(@MediaStatus.Status int status) {
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

