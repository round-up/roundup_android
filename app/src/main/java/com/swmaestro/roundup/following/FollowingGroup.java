package com.swmaestro.roundup.following;

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
    private String icon;

    public FollowingGroup() {
    }

    public FollowingGroup(long groupId, String title, boolean isAlliance, String introText, String icon) {
        this.groupId = groupId;
        this.title = title;
        this.isAlliance = isAlliance;
        this.introText = introText;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
