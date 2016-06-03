package com.swmaestro.roundup.following;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JeongMinCha on 16. 6. 3..
 */
public class FollowingGroup extends RealmObject {
    @PrimaryKey
    private long groupId;
    private String title;
    private boolean isAlliance;
    private String introText;
    private int iconRes;

    public FollowingGroup() {
    }

    public FollowingGroup(long groupId, String title, boolean isAlliance, String introText, int iconRes) {
        this.groupId = groupId;
        this.title = title;
        this.isAlliance = isAlliance;
        this.introText = introText;
        this.iconRes = iconRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public boolean isAlliance() {
        return isAlliance;
    }

    public void setAlliance(boolean alliance) {
        isAlliance = alliance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
