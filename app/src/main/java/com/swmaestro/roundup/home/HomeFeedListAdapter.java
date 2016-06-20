package com.swmaestro.roundup.home;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swmaestro.roundup.R;

import org.json.JSONObject;

/**
 * Created by JeongMinCha on 16. 4. 28..
 */
public class HomeFeedListAdapter
        extends RecyclerView.Adapter<HomeFeedListAdapter.ViewHolder> {

    JSONObject data;
    Context mContext;
    OnItemClickListener mItemClickListener;

    public HomeFeedListAdapter(Context context) {

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_home_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeFeed homeFeed = new HomeFeed();
        homeFeed.setGroupName("소프트웨어 마에스트로");
        homeFeed.setAuthorName("차정민");
        homeFeed.setTime("2016.06.11/16:00");
        homeFeed.setFeedTitle("재미있는 안드로이드 개발!");
        homeFeed.setFeedContent("안드로이드 개발은 재밌다안드로이드 개발은 재밌다안드로이드 개발은 재밌다안드로이드 개발은 재밌다안드로이드 개발은 재밌다안드로이드 개발은 재밌다");
        homeFeed.setNumRecommends(5);
        homeFeed.setNumComments(2);

        holder.groupName.setText(homeFeed.getGroupName());
        holder.authorName.setText(homeFeed.getAuthorName());
        holder.txtTime.setText(homeFeed.getTime());
        holder.feedTitle.setText(homeFeed.getFeedTitle());
        holder.feedContent.setText(homeFeed.getFeedContent());
        holder.txtRecommend.setText("추천 " + homeFeed.getNumRecommends() + " 건");
        holder.txtComment.setText("댓글 " + homeFeed.getNumComments() + " 건" );
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
        TextView authorName;
        TextView txtTime;
        TextView feedTitle;
        TextView feedContent;
        TextView txtRecommend;
        TextView txtComment;
        ImageButton btnHeart;
        ImageButton btnComment;
        ImageButton btnShare;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.home_feed_card);
            groupIcon = (ImageView) itemView.findViewById(R.id.img_group_icon);
            groupName = (TextView) itemView.findViewById(R.id.txt_group_name);
            authorName = (TextView) itemView.findViewById(R.id.txt_author_name);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
            feedTitle = (TextView) itemView.findViewById(R.id.txt_home_feed_title);
            feedContent = (TextView) itemView.findViewById(R.id.txt_home_feed_content);
            txtRecommend = (TextView) itemView.findViewById(R.id.txt_home_feed_recommends);
            txtComment = (TextView) itemView.findViewById(R.id.txt_home_feed_comments);
            btnHeart = (ImageButton) itemView.findViewById(R.id.btn_heart_home_feed);
            btnComment = (ImageButton) itemView.findViewById(R.id.btn_comment_home_feed);
            btnShare = (ImageButton) itemView.findViewById(R.id.btn_share_home_feed);

            btnHeart.setOnClickListener(this);
            btnComment.setOnClickListener(this);
            btnShare.setOnClickListener(this);
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
