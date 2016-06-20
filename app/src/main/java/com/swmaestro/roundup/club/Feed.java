
package com.swmaestro.roundup.club;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Feed {

    public static ArrayList<Feed> list;

    private int feed_access_modifier;
    private String feed_tags;
    private int like;
    private ArrayList<Bitmap> image_list;
    private String feed_title;
    private String feed_date;
    private String email;
    private String feed_type;
    private String feed_content;
    private ArrayList<Comment> comment_list;

    public int getFeed_access_modifier() {
        return feed_access_modifier;
    }

    public String getFeed_tags() {
        return feed_tags;
    }

    public int getLike() {
        return like;
    }

    public ArrayList<Bitmap> getImage_list() {
        return image_list;
    }

    public String getFeed_title() {
        return feed_title;
    }

    public String getFeed_date() {
        return feed_date;
    }

    public String getEmail() {
        return email;
    }

    public String getFeed_type() {
        return feed_type;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public ArrayList<Comment> getComment_list() {
        return comment_list;
    }

    public Feed(int feed_access_modifier, String feed_tags, int like, ArrayList<Bitmap> image_list, String feed_title, String feed_date, String email, String feed_type, String feed_content, ArrayList<Comment> comment_list) {
        this.feed_access_modifier = feed_access_modifier;
        this.feed_tags = feed_tags;
        this.like = like;
        this.image_list = image_list;
        this.feed_title = feed_title;
        this.feed_date = feed_date;
        this.email = email;
        this.feed_type = feed_type;
        this.feed_content = feed_content;
        this.comment_list = comment_list;
        list = new ArrayList<>();
    }
}
