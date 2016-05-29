package com.swmaestro.roundup.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swmaestro.roundup.R;

/**
 * Created by JeongMinCha on 16. 5. 6..
 */
public class ClubSummaryFeedListAdapter
        extends RecyclerView.Adapter<ClubSummaryFeedListAdapter.ViewHolder> {

    Context mContext;
    OnItemClickListener mItemClickListener;

    public ClubSummaryFeedListAdapter(Context context) {
        this.mContext = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_club_summary_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // example ClubSummaryFeed
        final ClubSummaryFeed clubSummaryFeed = new ClubSummaryFeed();
        clubSummaryFeed.clubName = "RoundUp";
        clubSummaryFeed.remainingDays = "5 days left";
        clubSummaryFeed.sessionSummary.add("1. First to do");
        clubSummaryFeed.sessionSummary.add("2. Second to do");
        clubSummaryFeed.sessionSummary.add("3. Third to do");

        // set texts to the visual components.
        holder.clubName.setText(clubSummaryFeed.clubName);
        holder.remainingDays.setText(clubSummaryFeed.remainingDays);
        StringBuilder builder = new StringBuilder();
        for (String summary: clubSummaryFeed.sessionSummary) {
            builder.append(summary + "\n");
        }
        holder.sessionSummary.setText(builder.toString());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public LinearLayout cardHolder;
        public TextView clubName;
        public TextView remainingDays;
        public TextView sessionSummary;

        public ViewHolder(View itemView) {
            super(itemView);
            cardHolder = (LinearLayout) itemView.findViewById(R.id.club_summary_main_holder);
            clubName = (TextView) itemView.findViewById(R.id.tv_club_name);
            remainingDays = (TextView) itemView.findViewById(R.id.tv_remaining_days);
            sessionSummary = (TextView) itemView.findViewById(R.id.tv_session_summary);

            cardHolder.setOnClickListener(this);
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
