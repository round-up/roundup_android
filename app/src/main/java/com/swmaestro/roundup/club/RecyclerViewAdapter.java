
package com.swmaestro.roundup.club;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swmaestro.roundup.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Feed> feedList;
    private Group group;
    final private int CLUBINFO = 0;
    final private int CLUBFEED = 1;

    public RecyclerViewAdapter(Context context, Group group) {
        this.context = context;
        feedList = new ArrayList<>();
        this.group = group;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        switch (viewType) {
            case CLUBINFO:
                View Clubview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_info, parent, false);
                Button btn_detail = (Button) Clubview.findViewById(R.id.btn_club_info_detail);

                btn_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(parent.getContext(), DetailClubActivity.class);

                        view.getContext().startActivity(intent);
                    }
                });
                return new ClubViewHolder(Clubview);
            case CLUBFEED:
                View Feedview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_club_feed, parent, false);
                return new FeedViewHolder(Feedview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position > 0) {
            position = position - 1;
            Feed feed = Feed.list.get(position);
            FeedViewHolder feedViewHolder = (FeedViewHolder) holder;
            feedViewHolder.textViewFeedTitle.setText(feed.getFeed_title());
            feedViewHolder.textViewFeedContent.setText(feed.getFeed_content());
            feedViewHolder.textViewLike.setText(feed.getLike() + "");
            feedViewHolder.textViewComment.setText(feed.getComment_list().size() + "");
            if(feed.getImage_list().size() >= 3){
                if(feed.getImage_list().size() == 2){
                    feedViewHolder.iv2.setVisibility(View.VISIBLE);
                    feedViewHolder.iv2_1.setImageBitmap(feed.getImage_list().get(0));
                    feedViewHolder.iv2_2.setImageBitmap(feed.getImage_list().get(1));
                }else{
                    feedViewHolder.iv3.setVisibility(View.VISIBLE);
                    feedViewHolder.iv3_1.setImageBitmap(feed.getImage_list().get(0));
                    feedViewHolder.iv3_2.setImageBitmap(feed.getImage_list().get(1));
                    feedViewHolder.iv3_3.setImageBitmap(feed.getImage_list().get(2));
                }
            }
//            Picasso.with(context).load(feed.getFeedFileThumbnail()).into(feedViewHolder.imageViewFeedFile);
            feedViewHolder.textViewFeedAuthor.setText(feed.getEmail());
            final int finalPosition = position;
            feedViewHolder.comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ReviewActivity.class);
                    intent.putExtra("position", finalPosition);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }else{
            ClubViewHolder clubViewHolder = (ClubViewHolder) holder;
            clubViewHolder.place.setText(group.getGroup_place());
            clubViewHolder.gisu.setText(group.getGroup_gisoo()+"");
            ArrayList<Bitmap> other_logo = group.getOther_logo();
            ArrayList<String> other_name = group.getOther_name();

            if(other_logo.size() > 0){
                clubViewHolder.iv_other1.setImageBitmap(other_logo.get(0));
                clubViewHolder.tv_other1.setText(other_name.get(0));
            }
            if(other_logo.size() > 1){
                clubViewHolder.iv_other2.setImageBitmap(other_logo.get(1));
                clubViewHolder.tv_other2.setText(other_name.get(1));
            }
            if(other_logo.size() > 2){
                clubViewHolder.iv_other3.setImageBitmap(other_logo.get(2));
                clubViewHolder.tv_other3.setText(other_name.get(2));
            }
        }
    }


    @Override
    public int getItemCount() {
        return feedList.size() + 1;
    }

    public int getItemViewType(int position) {
        if (position == 0)
            return CLUBINFO;
        else
            return CLUBFEED;
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder {

        TextView gisu;
        TextView place;
        ImageView iv_other1;
        ImageView iv_other2;
        ImageView iv_other3;
        TextView tv_other1;
        TextView tv_other2;
        TextView tv_other3;
        public ClubViewHolder(View itemView) {
            super(itemView);
            gisu = (TextView) itemView.findViewById(R.id.tv_club_info_gisu);
            place = (TextView) itemView.findViewById(R.id.tv_club_info_place);
            iv_other1 = (ImageView) itemView.findViewById(R.id.iv_club_info_other1);
            iv_other2 = (ImageView) itemView.findViewById(R.id.iv_club_info_other2);
            iv_other3 = (ImageView) itemView.findViewById(R.id.iv_club_info_other3);
            tv_other1 = (TextView) itemView.findViewById(R.id.tv_club_info_other1);
            tv_other2 = (TextView) itemView.findViewById(R.id.tv_club_info_other2);
            tv_other3 = (TextView) itemView.findViewById(R.id.tv_club_info_other3);
        }
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView textViewFeedTitle;
        TextView textViewFeedContent;
        TextView textViewFeedAuthor;
        TextView textViewComment;
        TextView textViewLike;
        LinearLayout iv2;
        LinearLayout iv3;
        ImageView iv3_1;
        ImageView iv3_2;
        ImageView iv3_3;

        ImageView iv2_1;
        ImageView iv2_2;

        ImageButton comment;

        public FeedViewHolder(View itemView) {
            super(itemView);
            textViewFeedContent = (TextView) itemView.findViewById(R.id.tv_feed_content);
            textViewFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
            textViewFeedAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            textViewComment = (TextView) itemView.findViewById(R.id.tv_feed_comment);
            textViewLike = (TextView) itemView.findViewById(R.id.tv_feed_like);
            iv3 = (LinearLayout) itemView.findViewById(R.id.three_layout);
            iv2 = (LinearLayout) itemView.findViewById(R.id.two_layout);

            iv3_1 = (ImageView) itemView.findViewById(R.id.three_1);
            iv3_2 = (ImageView) itemView.findViewById(R.id.three_2);
            iv3_3 = (ImageView) itemView.findViewById(R.id.three_3);

            iv2_1 = (ImageView) itemView.findViewById(R.id.two_1);
            iv2_2 = (ImageView) itemView.findViewById(R.id.two_2);

            comment = (ImageButton) itemView.findViewById(R.id.bt_comment);
        }
    }

}
