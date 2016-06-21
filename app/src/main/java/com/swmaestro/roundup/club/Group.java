package com.swmaestro.roundup.club;

import android.graphics.Bitmap;
import android.media.Image;
import android.provider.ContactsContract;

import com.swmaestro.roundup.utils.ImageHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lk on 16. 6. 18..
 */
public class Group {

    public static Group instance;

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
    private int group_gisoo;
    private ArrayList<Bitmap> other_logo;
    private ArrayList<String> other_name;
    private ArrayList<User> user_list;
    private Bitmap group_cover;
    public static ArrayList<Feed> list;

    private Group(int id, String group_belong, String group_category, String group_name, String group_description, String group_start_date, String group_place, boolean group_recruit_state, String group_leader_email, String group_logo, int gisu, String group_cover) {
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
        this.group_gisoo = gisu;
        other_logo = new ArrayList<>();
        other_name = new ArrayList<>();
        user_list = new ArrayList<>();
        this.group_cover = ImageHandler.getInstance().decodeBase64ToImage(group_cover);
        list = new ArrayList<>();
    }

    public Bitmap getGroup_cover() {
        return group_cover;
    }

    public static Group setGroup(int id, String group_belong, String group_category, String group_name, String group_description, String group_start_date, String group_place, boolean group_recruit_state, String group_leader_email, String group_logo, int gisu, String group_cover) {
        instance = new Group(id, group_belong, group_category, group_name, group_description, group_start_date, group_place, group_recruit_state, group_leader_email, group_logo, gisu, group_cover);
        return instance;
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

    public int getGroup_gisoo() {
        return group_gisoo;
    }

    public void setOther_logo(String image){
        other_logo.add(ImageHandler.getInstance().decodeBase64ToImage(image));
    }

    public ArrayList<String> getOther_name() {
        return other_name;
    }

    public ArrayList<Bitmap> getOther_logo() {
        return other_logo;
    }

    public void setOther_name(String name){
        other_name.add(name);
    }

    public void addUser(User user){
        user_list.add(user);
    }

    public ArrayList<User> getUser_list(){
        return user_list;
    }

    public Bitmap getGroup_logo() {
        return group_logo;
    }
}
