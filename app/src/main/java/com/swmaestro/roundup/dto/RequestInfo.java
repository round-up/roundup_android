package com.swmaestro.roundup.dto;

import org.json.JSONObject;

/**
 * Created by CHOI ILJI on 2016-06-02.
 */
public class RequestInfo {
    private String url;
    private String header;
    private JSONObject data;

    public RequestInfo(String url, String header, JSONObject data) {
        this.url = url;
        this.header = header;
        this.data = data;
    }

    public RequestInfo(String url, String header){
        this.url = url;
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public String[] getHeader() {
        String[] result = header.split(" ");
        return result;
    }

    public JSONObject getData() {
        return data;
    }
}
