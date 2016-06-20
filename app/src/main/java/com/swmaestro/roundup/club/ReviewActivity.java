package com.swmaestro.roundup.club;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.swmaestro.roundup.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReviewActivity extends ActionBarActivity {
    private ListView mListView;
    private EditText mCommentEt;
    private Button mRegisterBT;
    public ArrayList<Comment> reviewListData;
    private int commentId;

    private ProgressDialog progressDialog;

    private ReviewListAdapter reviewListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        init();
        loadData();
    }

    private void loadData() {
    }

    private void init() {
        Intent intent = getIntent();
        commentId = intent.getIntExtra("position", 0);

        mListView = (ListView) findViewById(R.id.activity_review_lv);
        mCommentEt = (EditText) findViewById(R.id.activity_review_commentET);
        mRegisterBT = (Button) findViewById(R.id.activity_review_registerBt);

        ListView listView = (ListView) findViewById(R.id.activity_review_lv);
        reviewListData = Feed.list.get(commentId).getComment_list();
        reviewListAdapter = new ReviewListAdapter(this, R.layout.item_comment,reviewListData);
        listView.setAdapter(reviewListAdapter);
        reviewListAdapter.notifyDataSetChanged();
        mRegisterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerComment(mCommentEt.getText().toString());
            }
        });

    }





    private void registerComment(String content) {
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//
//        HashMap<String, String> request = new HashMap<>();
//        request.put("model", Request.Method.POST+"");
//        request.put("url", AppSetting.reviewUrl);
//        request.put("recipe", recipeId);
//        request.put("content", content);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(request, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Volley", "Reiveiw Request : " + error.networkResponse.data.toString());
//            }
//        });
//        queue.add(jsonObjectRequest);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Comment c = new Comment("ds@ds.ds", format.format(date), content, "^^");
        reviewListData.add(c);
        reviewListAdapter.notifyDataSetChanged();
    }

    private void hideprograssDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}