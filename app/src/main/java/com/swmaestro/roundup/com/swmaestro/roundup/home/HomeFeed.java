package com.swmaestro.roundup.com.swmaestro.roundup.home;

import android.content.Context;

/**
 * Created by JeongMinCha on 16. 4. 28..
 */
public class HomeFeed {

    public String name;
    public String imageName;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
    }
}