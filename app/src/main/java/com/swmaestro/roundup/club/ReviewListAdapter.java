package com.swmaestro.roundup.club;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swmaestro.roundup.R;

import java.io.InputStream;
import java.util.ArrayList;

public class ReviewListAdapter extends BaseAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Comment> reviewArraylist;

    public ReviewListAdapter(Context context, int layoutResourceId, ArrayList<Comment> listData){
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.reviewArraylist = listData;
        Log.i("listsize", reviewArraylist.size()+"");
    }

    @Override
    public int getCount() {
        return reviewArraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewArraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }
        TextView mUserNameTv = (TextView) row.findViewById(R.id.review_list_userName);
        TextView mCommentTv = (TextView) row.findViewById(R.id.review_list_comment);

        mUserNameTv.setText(reviewArraylist.get(position).getEmail()+"");
        mCommentTv.setText(reviewArraylist.get(position).getComment_content());

        return row;
    }
}