package com.swmaestro.roundup;

import java.util.ArrayList;

/**
 * Created by JeongMinCha on 16. 4. 28..
 */
public class HomeFeedData {

    public static String[] homeFeedNameArray = {"roundup1", "roundup2"};
    public static String[] homeFeedImageNameArray = {"clubphoto.jpg", "clubphoto.jpg"};

    public static ArrayList<HomeFeed> homeFeedList() {
        ArrayList<HomeFeed> list = new ArrayList<>();
        for (int index = 0; index < homeFeedNameArray.length; index++) {
            HomeFeed homeFeed = new HomeFeed();
            homeFeed.name = homeFeedNameArray[index];
            homeFeed.imageName = homeFeedImageNameArray[index];
            list.add(homeFeed);
        }
        return list;
    }
}
