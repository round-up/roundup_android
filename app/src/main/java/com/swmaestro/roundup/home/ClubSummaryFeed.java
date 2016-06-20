package com.swmaestro.roundup.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JeongMinCha on 16. 5. 6..
 */
public class ClubSummaryFeed {

    private int groupId;
    private String groupName;
    private int remainingDays;
    private List<String> sessionSummaries;

    public ClubSummaryFeed() {
        sessionSummaries = new ArrayList<String>();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public List<String> getSessionSummaries() {
        return sessionSummaries;
    }

    public void setSessionSummaries(List<String> sessionSummaries) {
        this.sessionSummaries = sessionSummaries;
    }
}
