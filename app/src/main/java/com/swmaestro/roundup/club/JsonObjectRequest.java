package com.swmaestro.roundup.club;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lk on 2015. 8. 2..
 */
public class JsonObjectRequest extends Request<JSONObject>{

    private Response.Listener<JSONObject> listener;
    private Map<String, String> mParams;

    public JsonObjectRequest(HashMap<String, String> request, Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        super(Integer.parseInt(request.get("model")), request.get("url"), errorListener);
        listener = successListener;

        if(request.get("model").equals(Method.POST+"")) {
            Log.i("post", "post");
            mParams = request;
            mParams.remove("model");
            mParams.remove("url");
        }else{
            mParams = new HashMap<>();
        }
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}