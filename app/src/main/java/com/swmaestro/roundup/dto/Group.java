package com.swmaestro.roundup.dto;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CHOI ILJI on 2016-06-02.
 */
public class Group {
    private String groupLeaderEmail;
    private String groupName;
    //private XX logo;
    private String groupPlace;
    private String groupBelong;
    private String groupFoundation;
    private boolean groupEnroll;
    //private XX groupConverImage;

    public Group(String groupLeaderEmail, String groupName, String groupPlace, String groupBelong, String groupFoundation, boolean groupEnroll){
        this.groupLeaderEmail = "choiilji@gmail.com";//groupLeaderEmail;
        this.groupName = groupName;
        this.groupPlace = groupPlace;
        this.groupBelong = groupBelong;
        this.groupFoundation = groupFoundation;
        this.groupEnroll = groupEnroll;
    }

    public JSONObject getJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("group_leader_email", this.groupLeaderEmail);
            obj.put("group_belong", groupBelong);
            obj.put("group_category", "");
            obj.put("group_name", groupName);
            obj.put("group_description", "");
            obj.put("group_start_date", groupFoundation);
            obj.put("group_place", groupPlace);
            //
            obj.put("group_description", "1");
            obj.put("group_category", "1");
            //obj.put("group_logo", );
            //obj.put("group_cover", );
            obj.put("group_recruit_state", groupEnroll);
        }catch(JSONException je){
            Log.e("JSON Error", je.getMessage());
            je.printStackTrace();
        }
        return obj;
    }
}
