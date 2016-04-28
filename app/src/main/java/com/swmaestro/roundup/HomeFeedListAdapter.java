package com.swmaestro.roundup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by JeongMinCha on 16. 4. 28..
 */
public class HomeFeedListAdapter
        extends RecyclerView.Adapter<HomeFeedListAdapter.ViewHolder> {

    Context mContext;
    OnItemClickListener mItemClickListener;

    public HomeFeedListAdapter(Context context) {

        this.mContext = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_home_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeFeed homeFeed = HomeFeedData.homeFeedList().get(position);
        holder.cardName.setText(homeFeed.name);
        Picasso.with(mContext)
                .load(homeFeed.getImageResourceId(mContext))
                .into(holder.cardImage);
    }

    @Override
    public int getItemCount() {
        return HomeFeedData.size;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public LinearLayout cardHolder;
        public LinearLayout cardNameHolder;
        public TextView cardName;
        public ImageView cardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardHolder = (LinearLayout) itemView.findViewById(R.id.home_card_main_holder);
            cardNameHolder = (LinearLayout) itemView.findViewById(R.id.home_card_name_holder);
            cardName = (TextView) itemView.findViewById(R.id.home_card_name);
            cardImage = (ImageView) itemView.findViewById(R.id.home_card_image);

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
