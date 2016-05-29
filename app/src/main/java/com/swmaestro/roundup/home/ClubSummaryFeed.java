package com.swmaestro.roundup.home;

import java.util.ArrayList;

/**
 * Created by JeongMinCha on 16. 5. 6..
 */
public class ClubSummaryFeed {

    public String clubName;
    public String remainingDays;
    public ArrayList<String> sessionSummary;

    public ClubSummaryFeed() {
        sessionSummary = new ArrayList<String>();
    }
}
