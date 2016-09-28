package com.mark.vvideo.bilibili.model.entry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/28.
 * Function: 视频信息
 */

public class VideoInfo {


    /**
     * format : mp4
     * timelength : 1449098
     * accept_format : mp4,hdmp4
     * accept_quality : [2,1]
     * durl : [{"length":1449098,"size":106231838,"url":"http://cn-sdjn-cu-v-01.acgvideo.com/vg2/2/c4/10400078-1.mp4?expires=1475076900&ssig=bXb72Ix7pAhgg6ggScdSEg&oi=2067479167&rate=400000","backup_url":["http://cn-tj8-cu.acgvideo.com/vg2/d/8b/10400078-1.mp4?expires=1475076900&ssig=Vp49uYxOYWOWzWNXRQSkjA&oi=2067479167&rate=400000","http://cn-sddz2-cu.acgvideo.com/vg1/8/37/10400078-1.mp4?expires=1475076900&ssig=if0F1IcGLAbLbC11xDs6dA&oi=2067479167&rate=400000"]}]
     */

    @SerializedName("format")
    private String format;
    @SerializedName("timelength")
    private int timelength;
    @SerializedName("accept_format")
    private String acceptFormat;
    @SerializedName("accept_quality")
    private List<Integer> acceptQuality;
    /**
     * length : 1449098
     * size : 106231838
     * url : http://cn-sdjn-cu-v-01.acgvideo.com/vg2/2/c4/10400078-1.mp4?expires=1475076900&ssig=bXb72Ix7pAhgg6ggScdSEg&oi=2067479167&rate=400000
     * backup_url : ["http://cn-tj8-cu.acgvideo.com/vg2/d/8b/10400078-1.mp4?expires=1475076900&ssig=Vp49uYxOYWOWzWNXRQSkjA&oi=2067479167&rate=400000","http://cn-sddz2-cu.acgvideo.com/vg1/8/37/10400078-1.mp4?expires=1475076900&ssig=if0F1IcGLAbLbC11xDs6dA&oi=2067479167&rate=400000"]
     */

    @SerializedName("durl")
    private List<DurlBean> durl;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTimelength() {
        return timelength;
    }

    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }

    public String getAcceptFormat() {
        return acceptFormat;
    }

    public void setAcceptFormat(String acceptFormat) {
        this.acceptFormat = acceptFormat;
    }

    public List<Integer> getAcceptQuality() {
        return acceptQuality;
    }

    public void setAcceptQuality(List<Integer> acceptQuality) {
        this.acceptQuality = acceptQuality;
    }

    public List<DurlBean> getDurl() {
        return durl;
    }

    public void setDurl(List<DurlBean> durl) {
        this.durl = durl;
    }

    public static class DurlBean {
        @SerializedName("length")
        private int length;
        @SerializedName("size")
        private int size;
        @SerializedName("url")
        private String url;
        @SerializedName("backup_url")
        private List<String> backupUrl;

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getBackupUrl() {
            return backupUrl;
        }

        public void setBackupUrl(List<String> backupUrl) {
            this.backupUrl = backupUrl;
        }
    }
}
