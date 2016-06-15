package com.swmaestro.roundup.dto;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CHOI ILJI on 2016-06-02.
 */
public class Group {
    private String groupBackgroundImage; // will be encoded in Base64
    private String groupProfileImage; // will be encoded in Base64

    private String groupLeaderEmail;
    private String groupName;
    private String groupPlace;
    private String groupBelong;
    private String groupFoundation;
    private boolean groupIsRecruiting;

    public Group() {
    }

    public Group(String groupBackgroundImage, String groupProfileImage, String groupLeaderEmail, String groupName, String groupPlace, String groupBelong, String groupFoundation, boolean groupEnroll) {
        this.groupBackgroundImage = groupBackgroundImage;
        this.groupProfileImage = groupProfileImage;
        this.groupLeaderEmail = "choiilji@gmail.com";
        this.groupName = groupName;
        this.groupPlace = groupPlace;
        this.groupBelong = groupBelong;
        this.groupFoundation = groupFoundation;
        this.groupIsRecruiting = groupEnroll;
    }

    public JSONObject getJsonObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("group_logo", this.groupProfileImage);
            obj.put("group_cover", this.groupBackgroundImage);

            obj.put("group_leader_email", this.groupLeaderEmail);
            obj.put("group_name", groupName);
            obj.put("group_place", groupPlace);
            obj.put("group_belong", groupBelong);
            obj.put("group_start_date", groupFoundation);

            obj.put("group_description", "1");
            obj.put("group_category", "1");

            obj.put("group_recruit_state", groupIsRecruiting);
        }catch(JSONException je){
            Log.e("JSON Error", je.getMessage());
            je.printStackTrace();
        }
        return obj;
    }

    public String getGroupBackgroundImage() {
        return groupBackgroundImage;
    }

    public void setGroupBackgroundImage(String groupBackgroundImage) {
        this.groupBackgroundImage = groupBackgroundImage;
    }

    public String getGroupProfileImage() {
        return groupProfileImage;
    }

    public void setGroupProfileImage(String groupProfileImage) {
        this.groupProfileImage = groupProfileImage;
    }

    public String getGroupLeaderEmail() {
        return groupLeaderEmail;
    }

    public void setGroupLeaderEmail(String groupLeaderEmail) {
        this.groupLeaderEmail = groupLeaderEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPlace() {
        return groupPlace;
    }

    public void setGroupPlace(String groupPlace) {
        this.groupPlace = groupPlace;
    }

    public String getGroupBelong() {
        return groupBelong;
    }

    public void setGroupBelong(String groupBelong) {
        this.groupBelong = groupBelong;
    }

    public String getGroupFoundation() {
        return groupFoundation;
    }

    public void setGroupFoundation(String groupFoundation) {
        this.groupFoundation = groupFoundation;
    }

    public boolean isGroupIsRecruiting() {
        return groupIsRecruiting;
    }

    public void setGroupIsRecruiting(boolean groupIsRecruiting) {
        this.groupIsRecruiting = groupIsRecruiting;
    }
}
