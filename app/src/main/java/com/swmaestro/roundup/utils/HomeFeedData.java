package com.swmaestro.roundup.utils;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.swmaestro.roundup.server_connector.ServerConfig;

import org.json.JSONObject;

/**
 * Created by JeongMinCha on 16. 6. 21..
 */
public class HomeFeedData {
    private static HomeFeedData instance;
    private JSONObject data = null;

    public static HomeFeedData getInstance(){
        if(instance == null){
            instance = new HomeFeedData();
        }
        return instance;
    }

    public HomeFeedData() {
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public void loadData(Context context, String userEmail) {
        String url = ServerConfig.BASE_URL + "home_feed/" + userEmail;

        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                data = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        Volley.newRequestQueue(context).add(request);
    }
}
