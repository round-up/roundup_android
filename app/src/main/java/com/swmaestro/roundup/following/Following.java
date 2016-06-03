package com.swmaestro.roundup.following;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JeongMinCha on 16. 5. 30..
 */
public class Following extends RealmObject {
    @PrimaryKey
    private long id;
    private String title;
    private int iconRes;

    public Following() {}

    public Following(long id, String title, int iconRes) {
        this.id = id;
        this.title = title;
        this.iconRes = iconRes;
    }
    public long getId() { return this.id; }
    public String getTitle() { return this.title; }
    public int getIconRes() { return this.iconRes; }
    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setIconRes(int iconRes) { this.iconRes = iconRes; }
}
