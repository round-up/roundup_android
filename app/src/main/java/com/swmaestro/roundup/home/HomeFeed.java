package com.swmaestro.roundup.home;

import android.content.Context;

/**
 * Created by JeongMinCha on 16. 4. 28..
 */
public class HomeFeed {

    private String iconPath;
    private String groupName;
    private String authorName;
    private String time;
    private String feedTitle;
    private String feedContent;
    private int numRecommends;
    private int numComments;

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.iconPath, "drawable", context.getPackageName());
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public int getNumRecommends() {
        return numRecommends;
    }

    public void setNumRecommends(int numRecommends) {
        this.numRecommends = numRecommends;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }
}
