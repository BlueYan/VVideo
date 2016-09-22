package com.mark.vvideo.bilibili.model.entry;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function: 视频简介
 */

public class Introduction {


    /**
     * tid : 33
     * typename : 连载动画
     * arctype : Copy
     * play : 3818734
     * review : 53572
     * video_review : 227686
     * favorites : 2470
     * title : 【4月】Re：从零开始的异世界生活 19
     * description : #19 白鲸攻略战
     * tag : TV动画,BILIBILI正版,RE：从零开始的异世界生活,从零开始的异世界生活
     * pic : http://i2.hdslb.com/bfs/archive/a0656101763a68a4bcb3fe603496037c253e106d.jpg
     * author : TV-TOKYO
     * mid : 21453565
     * face : http://i0.hdslb.com/bfs/face/69ef6861067d6ef637b7c73b77d71c3414996745.jpg
     * pages : 1
     * created_at : 2016-08-08 01:05
     * coins : 18527
     * list : {"0":{"page":1,"type":"vupload","part":"ReZERO_19","cid":9253164,"vid":0}}
     */

    private int tid;
    private String typename;
    private String arctype;
    private String play;
    private String review;
    private String video_review;
    private String favorites;
    private String title;
    private String description;
    private String tag;
    private String pic;
    private String author;
    private String mid;
    private String face;
    private int pages;
    private String created_at;
    private String coins;
    /**
     * 0 : {"page":1,"type":"vupload","part":"ReZERO_19","cid":9253164,"vid":0}
     */

   // private ListBean list;


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

    public String getVideo_review() {
        return video_review;
    }

    public void setVideo_review(String video_review) {
        this.video_review = video_review;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "Introduction{" +
                "tid=" + tid +
                ", typename='" + typename + '\'' +
                ", arctype='" + arctype + '\'' +
                ", play='" + play + '\'' +
                ", review='" + review + '\'' +
                ", video_review='" + video_review + '\'' +
                ", favorites='" + favorites + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", pic='" + pic + '\'' +
                ", author='" + author + '\'' +
                ", mid='" + mid + '\'' +
                ", face='" + face + '\'' +
                ", pages=" + pages +
                ", created_at='" + created_at + '\'' +
                ", coins='" + coins + '\'' +
                '}';
    }
}
