package com.swmaestro.roundup.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.utils.ImageHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongMinCha on 16. 5. 6..
 */
public class ClubSummaryFeedListAdapter
        extends RecyclerView.Adapter<ClubSummaryFeedListAdapter.ViewHolder> {

    JSONObject data;
    JSONArray groupData;
    Context mContext;
    OnItemClickListener mItemClickListener;

    public ClubSummaryFeedListAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONArray getGroupData() {
        return groupData;
    }

    public void setGroupData(JSONArray groupData) {
        this.groupData = groupData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_summary_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // example ClubSummaryFeed
        final ClubSummaryFeed clubSummaryFeed = new ClubSummaryFeed();
        clubSummaryFeed.setGroupName("RoundUp");
        clubSummaryFeed.setRemainingDays(5);

        List<String> sessionTitles = new ArrayList<>();
        try {
            JSONArray sessionArray = data.getJSONArray("session");
            for (int idx = 0; idx < sessionArray.length(); idx++) {
                sessionTitles.add(sessionArray.getJSONObject(idx).getString("feed_title"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // set texts to the visual components.
        Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.p01_img_profile1);
        holder.groupIcon.setImageBitmap(ImageHandler.getInstance().getRoundedShape(bm));
        holder.groupName.setText(clubSummaryFeed.getGroupName());
        holder.groupSchedule.setText(clubSummaryFeed.getRemainingDays() + "일 남았습니다.");
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView tv = null;

        for (int idx = 0; idx < sessionTitles.size(); idx++) {
            tv = (TextView) inflater.inflate(R.layout.session_summary_item, null);
            tv.setText(sessionTitles.get(idx));
            holder.groupSessionSummary.addView(tv);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        CardView cardView;
        ImageView groupIcon;
        TextView groupName;
        TextView groupSchedule;
        LinearLayout groupSessionSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.club_summary_card);
            groupIcon = (ImageView) itemView.findViewById(R.id.img_group_icon);
            groupName = (TextView) itemView.findViewById(R.id.txt_group_name);
            groupSchedule = (TextView) itemView.findViewById(R.id.txt_group_schedule);
            groupSessionSummary = (LinearLayout) itemView.findViewById(R.id.layout_session_summary);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
