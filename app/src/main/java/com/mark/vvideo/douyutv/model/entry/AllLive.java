package com.mark.vvideo.douyutv.model.entry;

import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/22.
 * Function:
 */

public class AllLive {

    private int error;
    /**
     * room_id : 16101
     * room_src : https://rpic.douyucdn.cn/z1609/22/17/16101_160922171916.jpg
     * vertical_src : https://rpic.douyucdn.cn/z1609/22/17/16101_160922171916.jpg
     * isVertical : 0
     * cate_id : 1
     * room_name : 微笑： 给我AD我来CARRY
     * show_status : 1
     * subject :
     * show_time : 1474526976
     * owner_uid : 391270
     * specific_catalog : weixiao
     * specific_status : 1
     * vod_quality : 0
     * nickname : 微笑
     * online : 466835
     * url : /weixiao
     * game_url : /directory/game/LOL
     * game_name : 英雄联盟
     * child_id : 35
     * avatar : https://apic.douyucdn.cn/upload/avatar/000/39/12/70_avatar_big.jpg
     * avatar_mid : https://apic.douyucdn.cn/upload/avatar/000/39/12/70_avatar_middle.jpg
     * avatar_small : https://apic.douyucdn.cn/upload/avatar/000/39/12/70_avatar_small.jpg
     * fans : 1524392
     * ranktype : 0
     * anchor_city : 鱼塘
     */

    private List<Data> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String room_id;
        private String room_src;
        private String vertical_src;
        private int isVertical;
        private String cate_id;
        private String room_name;
        private String show_status;
        private String subject;
        private String show_time;
        private String owner_uid;
        private String specific_catalog;
        private String specific_status;
        private String vod_quality;
        private String nickname;
        private int online;
        private String url;
        private String game_url;
        private String game_name;
        private String child_id;
        private String avatar;
        private String avatar_mid;
        private String avatar_small;
        private String fans;
        private int ranktype;
        private String anchor_city;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_src() {
            return room_src;
        }

        public void setRoom_src(String room_src) {
            this.room_src = room_src;
        }

        public String getVertical_src() {
            return vertical_src;
        }

        public void setVertical_src(String vertical_src) {
            this.vertical_src = vertical_src;
        }

        public int getIsVertical() {
            return isVertical;
        }

        public void setIsVertical(int isVertical) {
            this.isVertical = isVertical;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getOwner_uid() {
            return owner_uid;
        }

        public void setOwner_uid(String owner_uid) {
            this.owner_uid = owner_uid;
        }

        public String getSpecific_catalog() {
            return specific_catalog;
        }

        public void setSpecific_catalog(String specific_catalog) {
            this.specific_catalog = specific_catalog;
        }

        public String getSpecific_status() {
            return specific_status;
        }

        public void setSpecific_status(String specific_status) {
            this.specific_status = specific_status;
        }

        public String getVod_quality() {
            return vod_quality;
        }

        public void setVod_quality(String vod_quality) {
            this.vod_quality = vod_quality;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getGame_url() {
            return game_url;
        }

        public void setGame_url(String game_url) {
            this.game_url = game_url;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getChild_id() {
            return child_id;
        }

        public void setChild_id(String child_id) {
            this.child_id = child_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar_mid() {
            return avatar_mid;
        }

        public void setAvatar_mid(String avatar_mid) {
            this.avatar_mid = avatar_mid;
        }

        public String getAvatar_small() {
            return avatar_small;
        }

        public void setAvatar_small(String avatar_small) {
            this.avatar_small = avatar_small;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public int getRanktype() {
            return ranktype;
        }

        public void setRanktype(int ranktype) {
            this.ranktype = ranktype;
        }

        public String getAnchor_city() {
            return anchor_city;
        }

        public void setAnchor_city(String anchor_city) {
            this.anchor_city = anchor_city;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "room_id='" + room_id + '\'' +
                    ", room_src='" + room_src + '\'' +
                    ", vertical_src='" + vertical_src + '\'' +
                    ", isVertical=" + isVertical +
                    ", cate_id='" + cate_id + '\'' +
                    ", room_name='" + room_name + '\'' +
                    ", show_status='" + show_status + '\'' +
                    ", subject='" + subject + '\'' +
                    ", show_time='" + show_time + '\'' +
                    ", owner_uid='" + owner_uid + '\'' +
                    ", specific_catalog='" + specific_catalog + '\'' +
                    ", specific_status='" + specific_status + '\'' +
                    ", vod_quality='" + vod_quality + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", online=" + online +
                    ", url='" + url + '\'' +
                    ", game_url='" + game_url + '\'' +
                    ", game_name='" + game_name + '\'' +
                    ", child_id='" + child_id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", avatar_mid='" + avatar_mid + '\'' +
                    ", avatar_small='" + avatar_small + '\'' +
                    ", fans='" + fans + '\'' +
                    ", ranktype=" + ranktype +
                    ", anchor_city='" + anchor_city + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AllLive{" +
                "error=" + error +
                ", data=" + data +
                '}';
    }
}
