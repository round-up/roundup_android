package com.swmaestro.roundup.dto;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by iljichoi on 16. 6. 11..
 */
public class GroupUsers {
    private int group_id;
    private String email;
    private int group_user_level;
    private int group_gisoo;

    public GroupUsers(int group_id, String email, int group_user_level, int group_gisoo) {
        this.group_id = group_id;
        this.email = email;
        this.group_user_level = group_user_level;
        this.group_gisoo = group_gisoo;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String getEmail() {
        return email;
    }

    public int getGroup_user_level() {
        return group_user_level;
    }

    public int getGroup_gisoo() {
        return group_gisoo;
    }

    public JSONObject getJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("group_id", group_id);
            obj.put("email", email);
            obj.put("group_user_level", group_user_level);
            obj.put("group_gisoo", group_gisoo);
        }catch(JSONException je){
            Log.e("JSON Error", je.getMessage());
            je.printStackTrace();
        }
        return obj;
    }
}
