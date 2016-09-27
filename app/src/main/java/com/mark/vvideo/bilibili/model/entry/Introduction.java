package com.mark.vvideo.bilibili.model.entry;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function: 视频简介
 */

public class Introduction implements Parcelable {

    /**
     * page : 1
     * type : vupload
     * part : Food War2 13
     * cid : 10400078
     * vid : 0
     */

    private ListBean ListBean;
    /**
     * tid : 33
     * typename : 连载动画
     * arctype : Copy
     * play : 1293522
     * review : 44
     * video_review : 58447
     * favorites : 1045
     * title : 【7月/完结】食戟之灵 贰之皿 13
     * description : #13 威风堂堂
     * tag : TV动画,BILIBILI正版,食戟之灵 贰之皿,食戟之灵 第二季
     * pic : http://i0.hdslb.com/bfs/archive/9fc8e0b22c2b75b602439acee5684e2583e485aa.jpg
     * author : 哔哩哔哩番剧
     * mid : 928123
     * face : http://i1.hdslb.com/bfs/face/60a9153609998b04301dc5b8ed44c41b537a2268.jpg
     * pages : 1
     * created_at : 2016-09-24 22:00
     * coins : 7667
     */

    @SerializedName("tid")
    private int tid;
    @SerializedName("typename")
    private String typename;
    @SerializedName("arctype")
    private String arctype;
    @SerializedName("play")
    private String play;
    @SerializedName("review")
    private String review;
    @SerializedName("video_review")
    private String videoReview;
    @SerializedName("favorites")
    private String favorites;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("tag")
    private String tag;
    @SerializedName("pic")
    private String pic;
    @SerializedName("author")
    private String author;
    @SerializedName("mid")
    private String mid;
    @SerializedName("face")
    private String face;
    @SerializedName("pages")
    private int pages;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("coins")
    private String coins;

    public ListBean getListBean() {
        return ListBean;
    }

    public void setListBean(ListBean ListBean) {
        this.ListBean = ListBean;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getArctype() {
        return arctype;
    }

    public void setArctype(String arctype) {
        this.arctype = arctype;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getVideoReview() {
        return videoReview;
    }

    public void setVideoReview(String videoReview) {
        this.videoReview = videoReview;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.tid);
        dest.writeString(this.typename);
        dest.writeString(this.arctype);
        dest.writeString(this.play);
        dest.writeString(this.review);
        dest.writeString(this.videoReview);
        dest.writeString(this.favorites);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.tag);
        dest.writeString(this.pic);
        dest.writeString(this.author);
        dest.writeString(this.mid);
        dest.writeString(this.face);
        dest.writeInt(this.pages);
        dest.writeString(this.createdAt);
        dest.writeString(this.coins);
    }

    public static class ListBean {
        @SerializedName("page")
        private int page;
        @SerializedName("type")
        private String type;
        @SerializedName("part")
        private String part;
        @SerializedName("cid")
        private int cid;
        @SerializedName("vid")
        private int vid;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "ListBean=" + ListBean +
                ", tid=" + tid +
                ", typename='" + typename + '\'' +
                ", arctype='" + arctype + '\'' +
                ", play='" + play + '\'' +
                ", review='" + review + '\'' +
                ", videoReview='" + videoReview + '\'' +
                ", favorites='" + favorites + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", pic='" + pic + '\'' +
                ", author='" + author + '\'' +
                ", mid='" + mid + '\'' +
                ", face='" + face + '\'' +
                ", pages=" + pages +
                ", createdAt='" + createdAt + '\'' +
                ", coins='" + coins + '\'' +
                '}';
    }
}
