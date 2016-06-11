package com.swmaestro.roundup.server_connector;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.util.Log;

import com.swmaestro.roundup.dto.RequestInfo;

import java.io.UnsupportedEncodingException;


/**
 * Created by CHOI ILJI on 2016-06-01.
 */
public class ServerConnector extends AsyncTask<String, String, String> {
    public static int POST = 0, GET = 1;
    private int callType;
    private RequestInfo info;

    public ServerConnector(int callType, RequestInfo info){
        this.callType = callType;
        this.info = info;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
     */
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        if (callType==POST) {
            // For test the server request;
            try {
                JSONObject obj = postInsert(info);
                return convertJSONtoString(obj);
            }catch(UnsupportedEncodingException ue){
                ue.printStackTrace();
                return "";
            }
        }else if(callType==GET){
            JSONObject obj = getOnly(info);
            return convertJSONtoString(obj);
        }else{
            return "";
        }
    }

    private String convertJSONtoString(JSONObject obj){
        return obj.toString();
    }

    private JSONObject getOnly(RequestInfo info){
        String url = info.getUrl();
        String[] header = info.getHeader();
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader(header[0], header[1]);
        JSONObject result = null;
        try{
            HttpResponse response = client.execute(get);
            String content = EntityUtils.toString(response.getEntity());
            result = new JSONObject(content);
        }catch(Exception e){
            Log.e("ERror", e.getMessage());
        }
        return result;
    }

    private JSONObject postOnly(RequestInfo info){
        String url = info.getUrl();
        String[] header = info.getHeader();
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader(header[0], header[1]);
        Log.e("info", url + " "+ header[0] + " "+header[1]);
        JSONObject result = null;
        try{
            HttpResponse response = client.execute(post);
            String content = EntityUtils.toString(response.getEntity());
            result = new JSONObject(content);
        }catch(Exception e){
            Log.e("ERror", e.getMessage());
        }
        return result;
    }

    private JSONObject postInsert(RequestInfo info) throws UnsupportedEncodingException{
        String url = info.getUrl();
        String[] header = info.getHeader();
        HttpClient client = new DefaultHttpClient();
        StringEntity se = new StringEntity(info.getData().toString());

        HttpPost post = new HttpPost(url);
        post.setHeader(header[0], header[1]);
        post.setEntity(se);
        Log.i("iasdasdnfo", url + " "+ header[0] + " "+header[1]);
        Log.i("info2", "123///" + se.toString());

        JSONObject result = null;
        try{
            HttpResponse response = client.execute(post);
            String content = EntityUtils.toString(response.getEntity());
            result = new JSONObject(content);
        }catch(Exception e){
            Log.e("ERror", e.getMessage());
        }
        return result;
    }
}
