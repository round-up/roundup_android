package com.swmaestro.roundup.dto;

/**
 * Created by iljichoi on 16. 6. 11..
 */
public class GroupUserLevel {
    private int group_id;
    private String level_title;

    public GroupUserLevel(int group_id, String level_title) {
        this.group_id = group_id;
        this.level_title = level_title;
    }

    public int getGroup_id() {
        return group_id;
    }

    public String getLevel_title() {
        return level_title;
    }
}