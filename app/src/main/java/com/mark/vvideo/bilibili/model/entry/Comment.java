package com.mark.vvideo.bilibili.model.entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Mark.
 * Date: 2016/9/21.
 * Function:
 */

public class Comment {
    public int owner;

    public int pages;

    public boolean needCode;

    public int isAdmin;

    public int results;

    //热门评论
    public List<HotList> hotList;

    //普通评论
    public List<ListBean> list;

    public int page;


    public static class HotList
    {

        public String face;

        public int mid;

        public String sex;

        public int isgood;

        public int adCheck;

        public String nick;

        public String create_at;

        public int rank;

        public int good;

        public LevelInfo level_info;

        public int lv;

        public String fbid;

        public int reply_count;

        public String msg;

        public int create;

        public String device;
    }

    public class ListBean
    {

        public String face;

        public int mid;

        public String sex;

        public int isgood;

        public int adCheck;

        public String nick;

        public String create_at;

        public int rank;

        public int good;

        public LevelInfo level_info;

        public int lv;

        public String fbid;

        public int reply_count;

        public String msg;

        public int create;

        public String device;
    }

    public class LevelInfo
    {

        public int current_level;
    }
}
