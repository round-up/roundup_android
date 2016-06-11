package com.swmaestro.roundup.server_connector;

import android.util.Log;

import com.swmaestro.roundup.dto.Group;
import com.swmaestro.roundup.dto.RequestInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CHOI ILJI on 2016-06-01.
 */
public class RequestConfigurations {
    private static final String BASE_URL = "http://10.0.3.2:8000/api_v1/";
    private static final String BASE_HEADER = "Content-type application/json";
    // Need to parse json file
    private JSONObject loadSettingsFromCfg() throws JSONException {
        JSONObject result = new JSONObject();
        result.put("base_url", "http://127.0.0.1:8000/");
        result.put("test_appendix", "api_v1/group_belong/");
        result.put("base_header", "Content-type application/json");

        return result;
    }
    public RequestInfo getAddGroupRequestInfo(String groupName, String groupPlace, String groupBelong, String groupFoundation, boolean groupEnroll){
        String appendix = "group/";
        String url = BASE_URL+appendix;
        Group groupInfo= new Group("", groupName, groupPlace, groupBelong, groupFoundation, groupEnroll);
        RequestInfo req = new RequestInfo(url, BASE_HEADER, groupInfo.getJson());
        return req;
    }

    public RequestInfo getGroupList(String email){
        String appendix = "group/";
        String url = BASE_URL + appendix;
        JSONObject req_data = new JSONObject();
        try {
            req_data.put("group_leader_email", email);
        } catch(JSONException je){
            Log.e("Server Request", "JSON Parsing Error");
            je.printStackTrace();
        }
        RequestInfo req = new RequestInfo(url, BASE_HEADER, req_data);
        return req;
    }
}
