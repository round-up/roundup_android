
package com.swmaestro.roundup.com.swmaestro.roundup.club;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swmaestro.roundup.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Feed> feedList;
    final private int CLUBINFO = 0;
    final private int CLUBFEED = 1;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        feedList = new ArrayList<>();
    }

    public void setFeedList(List<Feed> feedList){
        this.feedList = feedList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case CLUBINFO:
                View Clubview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_info, parent, false);
                return  new ClubViewHolder(Clubview);
            case CLUBFEED:
                View Feedview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_feed, parent, false);
                return new FeedViewHolder(Feedview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position > 0) {
            position = position - 1;
            Feed feed = feedList.get(position);
            FeedViewHolder feedViewHolder = (FeedViewHolder) holder;
            feedViewHolder.textViewFeedTitle.setText(feed.getFeedTitle());
            feedViewHolder.textViewFeedContent.setText(feed.getFeedContent());
            Picasso.with(context).load(feed.getFeedFileThumbnail()).into(feedViewHolder.imageViewFeedFile);
            feedViewHolder.textViewFeedAuthor.setText(feed.getFeedAuthor());
        }
    }


    @Override
    public int getItemCount() {
        return feedList.size() + 1;
    }

    public int getItemViewType(int position){
        if(position == 0)
            return CLUBINFO;
        else
            return CLUBFEED;
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder{

        public ClubViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView textViewFeedTitle;
        TextView textViewFeedContent;
        TextView textViewFeedAuthor;
        ImageView imageViewFeedFile;

        public FeedViewHolder(View itemView) {
            super(itemView);
            textViewFeedContent = (TextView) itemView.findViewById(R.id.tv_feed_content);
            textViewFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
            textViewFeedAuthor = (TextView) itemView.findViewById(R.id.tv_feed_author);
            imageViewFeedFile = (ImageView) itemView.findViewById(R.id.iv_feed_file);

        }
    }

}