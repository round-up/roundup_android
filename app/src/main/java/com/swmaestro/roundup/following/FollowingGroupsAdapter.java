package com.swmaestro.roundup.following;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swmaestro.roundup.R;
import com.swmaestro.roundup.utils.ImageHandler;

import java.util.List;

/**
 * Created by JeongMinCha on 16. 6. 3..
 */
public class FollowingGroupsAdapter extends ArrayAdapter<FollowingGroup> {
    private final Activity ctxt;
    private final int layoutResourceId = R.layout.following_group_item;
    private final List<FollowingGroup> followingGroups;

    static class ViewHolder {
        ImageView imgIcon;
        TextView txtName;
        TextView txtAlliance;
        TextView txtIntro;
    }

    public FollowingGroupsAdapter(Activity context, List<FollowingGroup> objects) {
        super(context, R.layout.following_group_item, objects);
        this.ctxt = context;
        this.followingGroups = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder holder = null;

        if (rowView == null) {
            LayoutInflater inflater = ctxt.getLayoutInflater();
            rowView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.imgIcon = (ImageView) rowView.findViewById(R.id.image_icon);
            holder.txtAlliance = (TextView) rowView.findViewById(R.id.text_alliance);
            holder.txtName = (TextView) rowView.findViewById(R.id.text_name);
            holder.txtIntro = (TextView) rowView.findViewById(R.id.text_introduction);
            rowView.setTag(holder);
        }

        holder = (ViewHolder) rowView.getTag();
        FollowingGroup group = followingGroups.get(position);
        Bitmap bm = ImageHandler.getInstance().decodeBase64(group.getIcon());
        bm = ImageHandler.getInstance().getRoundedShape(bm);

        holder.imgIcon.setImageBitmap(bm);
        holder.txtName.setText(group.getTitle());
        if (group.isAlliance() == true) {
            holder.txtAlliance.setText(" | 연합 관계");
        } else {
            holder.txtAlliance.setText("");
        }
        holder.txtIntro.setText(group.getIntroText());

        return rowView;
    }

}
