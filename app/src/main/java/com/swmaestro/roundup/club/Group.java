package com.swmaestro.roundup.club;

import android.graphics.Bitmap;

import com.swmaestro.roundup.utils.ImageHandler;

/**
 * Created by lk on 16. 6. 18..
 */
public class Group {


    private int id;
    private String group_belong;
    private String group_category;
    private String group_name;
    private String group_description;
    private String group_start_date;
    private String group_place;
    private boolean group_recruit_state;
    private String group_leader_email;
    private Bitmap group_logo;


    public Group(int id, String group_belong, String group_category, String group_name, String group_description, String group_start_date, String group_place, boolean group_recruit_state, String group_leader_email, String group_logo) {
        this.id = id;
        this.group_belong = group_belong;
        this.group_category = group_category;
        this.group_name = group_name;
        this.group_description = group_description;
        this.group_start_date = group_start_date;
        this.group_place = group_place;
        this.group_recruit_state = group_recruit_state;
        this.group_leader_email = group_leader_email;
        this.group_logo = ImageHandler.getInstance().decodeBase64ToImage(group_logo);
    }


    public int getId() {
        return id;
    }

    public String getGroup_belong() {
        return group_belong;
    }

    public String getGroup_category() {
        return group_category;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getGroup_description() {
        return group_description;
    }

    public String getGroup_start_date() {
        return group_start_date;
    }

    public String getGroup_place() {
        return group_place;
    }

    public boolean isGroup_recruit_state() {
        return group_recruit_state;
    }

    public String getGroup_leader_email() {
        return group_leader_email;
    }

    public Bitmap getGroup_logo() {
        return group_logo;
    }
}
